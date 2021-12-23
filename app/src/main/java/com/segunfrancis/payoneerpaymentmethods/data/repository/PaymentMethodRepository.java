package com.segunfrancis.payoneerpaymentmethods.data.repository;

import androidx.annotation.WorkerThread;

import com.segunfrancis.payoneerpaymentmethods.data.remote.PaymentMethodsApi;
import com.segunfrancis.payoneerpaymentmethods.data.remote.model.Response;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class PaymentMethodRepository implements IPaymentMethodRepository {

    private final PaymentMethodsApi api;

    @Inject
    public PaymentMethodRepository(PaymentMethodsApi api) {
        this.api = api;
    }

    @WorkerThread
    @Override
    public Single<Response> getPaymentMethods() {
        return api.loadPaymentMethods();
    }
}
