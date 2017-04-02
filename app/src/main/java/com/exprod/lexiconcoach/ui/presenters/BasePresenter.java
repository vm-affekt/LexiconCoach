package com.exprod.lexiconcoach.ui.presenters;

import android.content.Context;
import android.os.Bundle;

import com.exprod.lexiconcoach.LexiconCoachApp;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by VLAD on 29.03.2017.
 */

public abstract class BasePresenter<V> {
    @Inject
    protected CompositeSubscription mCompositeSubscription;

    private V mView;

    public BasePresenter(){
    }

    protected V getView(){
        return mView;
    }

    protected boolean isViewNotNull(){
        return mView != null;
    }

    protected void addSubscription(Subscription subscription){
        mCompositeSubscription.add(subscription);
    }

    public void onStop(){
        mView = null;
        mCompositeSubscription.clear();
    }

    public void bindView(V view){
        mView = view;
    }

    public abstract void onCreate(Bundle savedInstanceState);



}
