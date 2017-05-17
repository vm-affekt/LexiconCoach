package com.exprod.lexiconcoach.mvpmodels;

import android.content.Context;

import com.exprod.lexiconcoach.LexiconCoachApp;
import com.exprod.lexiconcoach.repositories.WordRepository;
import com.exprod.lexiconcoach.storage.entities.WordTranslationEntity;
import com.exprod.lexiconcoach.storage.entities.WordTranslationSpecialEntity;
import com.exprod.lexiconcoach.viewmodels.WordVM;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;

import java.util.List;

import javax.inject.Inject;

import rx.Completable;
import rx.Observable;

/**
 * Created by VLAD on 25.04.2017.
 */

public class WordMvpModelImpl implements WordMvpModel {

    @Inject
    protected WordRepository mRepository;

    public WordMvpModelImpl(Context context){
        LexiconCoachApp.get(context).getAppComponent().injectInto(this);
    }

    @Override
    public Observable<List<WordVM>> getAllWordsForVocabulary(Long vocabularyId) {
        return mRepository.getWordsForVocabulary(vocabularyId)
                .flatMapObservable(lst -> Observable.from(lst))
                .map(WordVM::mapFrom)
                .toList();

    }

    @Override
    public Observable<WordVM> getWordById(Long translationId) {
        return mRepository.getWordById(translationId)
                .map(WordVM::mapFrom)
                .toObservable();
    }

    @Override
    public Completable putWordTranslation(Long vocabularyId, WordVM model) {
        WordTranslationSpecialEntity wordTranslation = WordTranslationSpecialEntity.newInstance(
                model.getTranslationId(),
                model.getTranslationWord(),
                model.getNativeWord()
        );
        return mRepository.putWordTranslation(wordTranslation)
                .flatMapCompletable(id -> {
                    if (model.getTranslationId() == null){
                        return mRepository.attachTranslationToVocabulary(vocabularyId, id);
                    }else{
                        return Completable.complete();
                    }
                });
    }
}
