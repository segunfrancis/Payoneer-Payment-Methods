package com.segunfrancis.payoneerpaymentmethods.di;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.segunfrancis.payoneerpaymentmethods.data.remote.PaymentMethodsApi;
import com.segunfrancis.payoneerpaymentmethods.util.AppConstants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class RemoteModule {

    @NonNull
    @Provides
    @Singleton
    public static Gson provideGson() {
        return new GsonBuilder().setLenient().create();
    }

    @NonNull
    @Provides
    @Singleton
    public static HttpLoggingInterceptor provideInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @NonNull
    @Provides
    @Singleton
    public static OkHttpClient provideClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient().newBuilder()
                .callTimeout(AppConstants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }

    @NonNull
    @Provides
    @Singleton
    public static PaymentMethodsApi provideApi(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(PaymentMethodsApi.class);
    }
}
