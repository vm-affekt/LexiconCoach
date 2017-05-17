package com.exprod.lexiconcoach;

import android.content.Context;

import com.exprod.lexiconcoach.mvpmodels.VocabularyMvpModel;
import com.exprod.lexiconcoach.mvpmodels.VocabularyMvpModelImpl;
import com.exprod.lexiconcoach.mvpmodels.WordMvpModel;
import com.exprod.lexiconcoach.mvpmodels.WordMvpModelImpl;

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

    @Provides
    @Singleton
    public WordMvpModel provideWordMvpModel(Context context){
        return new WordMvpModelImpl(context);
    }
}
