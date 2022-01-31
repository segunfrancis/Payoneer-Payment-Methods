package com.segunfrancis.payoneerpaymentmethods.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.segunfrancis.payoneerpaymentmethods.base.BaseViewModel;
import com.segunfrancis.payoneerpaymentmethods.data.remote.model.ApplicableItem;
import com.segunfrancis.payoneerpaymentmethods.data.repository.IPaymentMethodRepository;
import com.segunfrancis.payoneerpaymentmethods.util.PaymentMethodState;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

@HiltViewModel
public class HomeViewModel extends BaseViewModel {

    private final IPaymentMethodRepository repository;

    private final MutableLiveData<PaymentMethodState> _state = new MutableLiveData<>();
    public LiveData<PaymentMethodState> state = _state;

    private final MutableLiveData<List<ApplicableItem>> _response = new MutableLiveData<>();
    public LiveData<List<ApplicableItem>> response = _response;

    private final MutableLiveData<Throwable> _error = new MutableLiveData<>();
    public LiveData<Throwable> error = _error;

    @Inject
    public HomeViewModel(@NonNull IPaymentMethodRepository repository) {
        this.repository = repository;

        loadPaymentMethods();
    }

    public void loadPaymentMethods() {
        _state.postValue(PaymentMethodState.IN_PROGRESS);
        Single<List<ApplicableItem>> items = Single.create(emitter -> {
            List<ApplicableItem> paymentMethods = Objects.requireNonNull(repository.getPaymentMethods().body())
                    .getNetworks().getApplicable();
            emitter.onSuccess(paymentMethods);
        });

        disposable.add(
                items.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess(applicableItems -> {
                            _state.postValue(PaymentMethodState.SUCCESS);
                            _response.postValue(applicableItems);
                        })
                        .doOnError(throwable -> {
                            _state.postValue(PaymentMethodState.ERROR);
                            _error.postValue(throwable);
                        })
                        .subscribe((applicableItems, throwable) -> Timber.e(throwable))
        );
    }
}
