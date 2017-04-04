package com.exprod.lexiconcoach.repositories;

import com.exprod.lexiconcoach.storage.entities.RunResultEntity;
import com.exprod.lexiconcoach.storage.entities.VocabularyEntity;
import com.exprod.lexiconcoach.viewmodels.PutVocabularyVM;

import java.util.List;

import rx.Completable;
import rx.Single;

/**
 * Created by VLAD on 02.04.2017.
 */

public interface VocabularyRepository {
    Single<List<VocabularyEntity>> getAllVocabularies();
    Completable putVocabulary(VocabularyEntity vocabulary);
    Single<VocabularyEntity> getVocabulary(Long vocabularyId);
    Single<Boolean> isVocabularyExists(String title);
    Single<RunResultEntity> getLastRunResultFor(Long vocabularyId);

}
