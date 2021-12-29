package com.segunfrancis.payoneerpaymentmethods.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.segunfrancis.payoneerpaymentmethods.data.remote.PaymentMethodsApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FakePaymentMethodApi {

    private static Gson fakeGson() {
        return new GsonBuilder().setLenient().create();
    }

    public static PaymentMethodsApi provideFakeApi() {
        return new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(fakeGson()))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(PaymentMethodsApi.class);
    }
}
