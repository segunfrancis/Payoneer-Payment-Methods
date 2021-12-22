package com.segunfrancis.payoneerpaymentmethods.data.repository;

import com.segunfrancis.payoneerpaymentmethods.data.remote.PaymentMethodsApi;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

public class PaymentMethodRepository implements IPaymentMethodRepository {

    private final PaymentMethodsApi api;

    @Inject
    public PaymentMethodRepository(PaymentMethodsApi api) {
        this.api = api;
    }

    @Override
    public void getPaymentMethods() {
        api.loadPaymentMethods().subscribeOn(Schedulers.io()).subscribe((response, throwable) -> {
            response.getResultCode();
        });
    }
}
