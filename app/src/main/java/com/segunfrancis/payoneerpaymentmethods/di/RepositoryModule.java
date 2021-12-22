package com.segunfrancis.payoneerpaymentmethods.di;

import com.segunfrancis.payoneerpaymentmethods.data.repository.PaymentMethodRepository;
import com.segunfrancis.payoneerpaymentmethods.data.repository.IPaymentMethodRepository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;

@Module
@InstallIn(ActivityRetainedComponent.class)
public interface RepositoryModule {

    @Binds
    IPaymentMethodRepository bindRepository(PaymentMethodRepository repository);
}
