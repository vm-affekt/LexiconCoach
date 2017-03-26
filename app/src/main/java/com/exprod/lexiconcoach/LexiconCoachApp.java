package com.exprod.lexiconcoach;

import android.app.Application;
import android.content.Context;

/**
 * Created by VLAD on 26.03.2017.
 */

public class LexiconCoachApp extends Application {

    private volatile AppComponent mAppComponent;

    public static LexiconCoachApp get(Context context){
        return (LexiconCoachApp) context.getApplicationContext();
    }

    public AppComponent getAppComponent(){
        if (mAppComponent == null){
            synchronized (LexiconCoachApp.class){
                if (mAppComponent == null){
                    mAppComponent = createAppComponent();
                }
            }
        }
        return mAppComponent;
    }

    public AppComponent createAppComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
