package com.grebnev.triprestaurant.common;

import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<View extends BaseView> extends MvpPresenter<View> {
    protected final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void disposeAll() {
        compositeDisposable.dispose();
    }
}
