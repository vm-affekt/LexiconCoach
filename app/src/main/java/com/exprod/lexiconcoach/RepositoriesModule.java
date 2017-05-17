package com.exprod.lexiconcoach;

import android.content.Context;

import com.exprod.lexiconcoach.repositories.VocabularyRepository;
import com.exprod.lexiconcoach.repositories.VocabularyRepositoryImpl;
import com.exprod.lexiconcoach.repositories.WordRepository;
import com.exprod.lexiconcoach.repositories.WordRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by VLAD on 02.04.2017.
 */

@Module
public class RepositoriesModule {
    @Singleton
    @Provides
    public VocabularyRepository provideVocabularyRepository(Context context){
        return new VocabularyRepositoryImpl(context);
    }

    @Singleton
    @Provides
    public WordRepository provideWordRepository(Context context){
        return new WordRepositoryImpl(context);
    }
}
