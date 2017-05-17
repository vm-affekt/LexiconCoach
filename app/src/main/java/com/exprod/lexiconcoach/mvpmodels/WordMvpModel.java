package com.exprod.lexiconcoach.mvpmodels;

import com.exprod.lexiconcoach.viewmodels.WordVM;

import java.util.List;

import rx.Completable;
import rx.Observable;

/**
 * Created by VLAD on 24.04.2017.
 */

public interface WordMvpModel {
    Observable<List<WordVM>> getAllWordsForVocabulary(Long vocabularyId);
    Observable<WordVM> getWordById(Long translationId);
    Completable putWordTranslation(Long vocabularyId, WordVM model);
}
