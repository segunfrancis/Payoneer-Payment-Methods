package com.segunfrancis.payoneerpaymentmethods;

import android.app.Application;

import timber.log.Timber;

public class PaymentMethodApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
