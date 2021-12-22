package com.segunfrancis.payoneerpaymentmethods.ui.home;

import androidx.lifecycle.ViewModel;

import com.segunfrancis.payoneerpaymentmethods.data.repository.IPaymentMethodRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    @Inject
    public HomeViewModel(IPaymentMethodRepository repository) {
        repository.getPaymentMethods();
    }
}
