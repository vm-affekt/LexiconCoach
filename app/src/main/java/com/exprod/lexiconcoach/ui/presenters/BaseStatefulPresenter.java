package com.exprod.lexiconcoach.ui.presenters;

/**
 * Created by VLAD on 02.04.2017.
 */

public abstract class BaseStatefulPresenter<V, S> extends BasePresenter<V>{
    private S mState;

    public abstract void bindState(S state);
    public abstract S getState();
}
