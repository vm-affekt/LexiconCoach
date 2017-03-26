package com.exprod.lexiconcoach;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by VLAD on 26.03.2017.
 */

@Module
public class AppModule {
    private final LexiconCoachApp mApp;

    public AppModule(LexiconCoachApp app) {
        mApp = app;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return mApp;
    }
}
