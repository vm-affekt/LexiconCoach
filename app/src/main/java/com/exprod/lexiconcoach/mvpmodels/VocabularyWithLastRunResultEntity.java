package com.exprod.lexiconcoach.mvpmodels;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.exprod.lexiconcoach.storage.entities.RunResultEntity;
import com.exprod.lexiconcoach.storage.entities.VocabularyEntity;

/**
 * Created by VLAD on 27.03.2017.
 */

public class VocabularyWithLastRunResultEntity {

    @NonNull
    private final VocabularyEntity mVocabulary;

    @Nullable
    private final RunResultEntity mRunResult;

    public VocabularyWithLastRunResultEntity(@NonNull VocabularyEntity vocabulary, RunResultEntity runResult) {
        mVocabulary = vocabulary;
        mRunResult = runResult;
    }

    public static VocabularyWithLastRunResultEntity newInstance(@NonNull VocabularyEntity vocabulary, RunResultEntity runResult){
        return new VocabularyWithLastRunResultEntity(vocabulary, runResult);
    }

    @NonNull
    public VocabularyEntity getVocabulary() {
        return mVocabulary;
    }

    @Nullable
    public RunResultEntity getRunResult() {
        return mRunResult;
    }
}
