package com.exprod.lexiconcoach.mvpmodels;

import com.exprod.lexiconcoach.storage.entities.RunResultEntity;
import com.exprod.lexiconcoach.storage.entities.VocabularyEntity;
import com.exprod.lexiconcoach.viewmodels.PutVocabularyVM;
import com.exprod.lexiconcoach.viewmodels.VocabularyItemVM;

import java.util.List;

import rx.Completable;
import rx.Observable;
import rx.Single;

/**
 * Created by VLAD on 27.03.2017.
 */

public interface VocabularyMvpModel {
    Observable<List<VocabularyItemVM>> getVocabularyItems();
    Observable<PutVocabularyVM> getPutVocabularyVM(Long vocabularyId);
    Completable addVocabulary(PutVocabularyVM model);
    Completable editVocabulary(PutVocabularyVM model);

}
