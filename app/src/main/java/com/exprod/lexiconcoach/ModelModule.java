package com.exprod.lexiconcoach;

import android.content.Context;

import com.exprod.lexiconcoach.mvpmodels.VocabularyMvpModel;
import com.exprod.lexiconcoach.mvpmodels.VocabularyMvpModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by VLAD on 27.03.2017.
 */

@Module
public class ModelModule {

    @Provides
    @Singleton
    public VocabularyMvpModel provideVocabularyMvpModel(Context context){
        return new VocabularyMvpModelImpl(context);
    }
}
