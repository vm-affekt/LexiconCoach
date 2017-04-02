package com.exprod.lexiconcoach.mappers;

import com.exprod.lexiconcoach.storage.entities.VocabularyEntity;
import com.exprod.lexiconcoach.viewmodels.VocabularyItemVM;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by VLAD on 28.03.2017.
 */

public class VocabularyItemViewModelMapper implements Func1<List<VocabularyEntity>, Observable<List<VocabularyItemVM>>> {

    @Inject
    public VocabularyItemViewModelMapper() {
    }

    @Override
    public Observable<List<VocabularyItemVM>> call(List<VocabularyEntity> vocabularyEntities) {
        return null;

    }
}
