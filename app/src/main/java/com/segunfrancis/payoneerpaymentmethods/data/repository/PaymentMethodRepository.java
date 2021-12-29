package com.segunfrancis.payoneerpaymentmethods.data.repository;

import androidx.annotation.WorkerThread;

import com.segunfrancis.payoneerpaymentmethods.data.remote.PaymentMethodsApi;
import com.segunfrancis.payoneerpaymentmethods.data.remote.model.BaseResponse;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;

public class PaymentMethodRepository implements IPaymentMethodRepository {

    private final PaymentMethodsApi api;

    @Inject
    public PaymentMethodRepository(PaymentMethodsApi api) {
        this.api = api;
    }

    @WorkerThread
    @Override
    public Response<BaseResponse> getPaymentMethods() throws IOException {
        return api.loadPaymentMethods().execute();
    }
}
