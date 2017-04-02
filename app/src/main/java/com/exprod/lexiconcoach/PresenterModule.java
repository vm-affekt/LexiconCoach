package com.exprod.lexiconcoach;

import android.content.Context;


import com.exprod.lexiconcoach.ui.presenters.PutVocabularyPresenter;
import com.exprod.lexiconcoach.ui.presenters.VocabularyListPresenter;
import com.exprod.lexiconcoach.ui.views.PutVocabularyView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by VLAD on 27.03.2017.
 */
@Module
public class PresenterModule {
    @Singleton
    @Provides
    public VocabularyListPresenter provideVocabularyListPresenter(Context context){
       return new VocabularyListPresenter(context);
    }

    @Singleton
    @Provides
    public PutVocabularyPresenter providePutVocabularyPresenter(Context context){
        return new PutVocabularyPresenter(context);
    }

    @Provides
    public CompositeSubscription provideCompositeSubscription(){
        return new CompositeSubscription();
    }
}
