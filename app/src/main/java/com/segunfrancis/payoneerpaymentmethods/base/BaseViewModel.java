package com.segunfrancis.payoneerpaymentmethods.base;

import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

/**
 * Base class for {@link androidx.lifecycle.ViewModel ViewModel}s. <br>
 * It handles disposing of the {@link io.reactivex.rxjava3.disposables.Disposable Disposable} in all ViewModel classes that inherits from it. <br>
 * This prevents duplication of code in each child ViewModel class
 */
public abstract class BaseViewModel extends ViewModel {

    protected CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        super.onCleared();
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
