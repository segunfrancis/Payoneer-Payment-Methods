package com.segunfrancis.payoneerpaymentmethods.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.segunfrancis.payoneerpaymentmethods.base.BaseViewModel;
import com.segunfrancis.payoneerpaymentmethods.data.repository.IPaymentMethodRepository;
import com.segunfrancis.payoneerpaymentmethods.util.PaymentMethodState;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

@HiltViewModel
public class HomeViewModel extends BaseViewModel {

    private final IPaymentMethodRepository repository;

    private final MutableLiveData<PaymentMethodState> _state = new MutableLiveData<>();
    public LiveData<PaymentMethodState> state = _state;

    private final MutableLiveData<List<PaymentMethod>> _response = new MutableLiveData<>();
    public LiveData<List<PaymentMethod>> response = _response;

    private final MutableLiveData<Throwable> _error = new MutableLiveData<>();
    public LiveData<Throwable> error = _error;

    @Inject
    public HomeViewModel(@NonNull IPaymentMethodRepository repository) {
        this.repository = repository;

        loadPaymentMethods();
    }

    public void loadPaymentMethods() {
        _state.postValue(PaymentMethodState.IN_PROGRESS);
        disposable.add(repository.getPaymentMethods()
                .map(response -> response.getNetworks().getApplicable())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(applicableItems -> {
                    _state.postValue(PaymentMethodState.SUCCESS);
                    List<PaymentMethod> paymentMethods = applicableItems.stream().map(applicableItem ->
                            new PaymentMethod(
                                    applicableItem.getCode(),
                                    applicableItem.getMethod(),
                                    applicableItem.getLabel(),
                                    applicableItem.getLabel(),
                                    applicableItem.getLinks().getLogo()
                            )
                    ).collect(Collectors.toList());
                    _response.postValue(paymentMethods);
                })
                .doOnError(throwable -> {
                    _state.postValue(PaymentMethodState.ERROR);
                    _error.postValue(throwable);
                })
                .subscribe((applicableItems, throwable) -> Timber.e(throwable))
        );
    }
}
