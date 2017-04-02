package com.exprod.lexiconcoach.viewmodels;

import android.support.annotation.Nullable;

/**
 * Created by VLAD on 29.03.2017.
 */

public class PutVocabularyVM {
    @Nullable
    private Long mVocabularyId;

    private String mTitle;
    private String mDescription;

    public PutVocabularyVM(String title, String description) {
        this.mTitle = title;
        this.mDescription = description;
    }

    public PutVocabularyVM(@Nullable Long vocabularyId, String title, String description) {
        this(title, description);
        this.mVocabularyId = vocabularyId;
    }

    @Nullable
    public Long getVocabularyId() {
        return mVocabularyId;
    }

    public void setVocabularyId(Long vocabularyId) {
        mVocabularyId = vocabularyId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
