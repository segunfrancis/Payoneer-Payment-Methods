package com.segunfrancis.payoneerpaymentmethods.data.remote;

import com.segunfrancis.payoneerpaymentmethods.data.remote.model.Response;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface PaymentMethodsApi {

    @GET("listresult.json")
    Single<Response> loadPaymentMethods();
}