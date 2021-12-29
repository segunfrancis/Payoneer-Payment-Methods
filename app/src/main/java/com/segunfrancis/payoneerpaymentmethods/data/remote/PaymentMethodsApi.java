package com.segunfrancis.payoneerpaymentmethods.data.remote;

import com.segunfrancis.payoneerpaymentmethods.data.remote.model.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PaymentMethodsApi {

    @GET("listresult.json")
    Call<BaseResponse> loadPaymentMethods();
}
