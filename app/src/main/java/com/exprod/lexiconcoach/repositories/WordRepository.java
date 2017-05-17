package com.exprod.lexiconcoach.repositories;

import com.exprod.lexiconcoach.storage.entities.WordTranslationSpecialEntity;
import com.pushtorefresh.storio.sqlite.operations.put.PutResult;

import java.util.List;

import rx.Completable;
import rx.Single;

/**
 * Created by VLAD on 05.05.2017.
 */

public interface WordRepository {
    Single<List<WordTranslationSpecialEntity>> getWordsForVocabulary(Long vocabularyId);
    Single<WordTranslationSpecialEntity> getWordById(Long translationId);
    Single<Long> putWordTranslation(WordTranslationSpecialEntity wordTranslation);
    Completable attachTranslationToVocabulary(Long vocabularyId, Long translationId);
}
