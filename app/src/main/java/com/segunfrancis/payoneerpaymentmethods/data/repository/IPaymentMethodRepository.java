package com.segunfrancis.payoneerpaymentmethods.data.repository;

import com.segunfrancis.payoneerpaymentmethods.data.remote.model.BaseResponse;

import java.io.IOException;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;

public interface IPaymentMethodRepository {
    Response<BaseResponse> getPaymentMethods() throws IOException;
}
