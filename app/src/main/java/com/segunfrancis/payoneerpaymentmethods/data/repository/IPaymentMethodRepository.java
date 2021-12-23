package com.segunfrancis.payoneerpaymentmethods.data.repository;

import com.segunfrancis.payoneerpaymentmethods.data.remote.model.Response;

import io.reactivex.rxjava3.core.Single;

public interface IPaymentMethodRepository {
    Single<Response> getPaymentMethods();
}
