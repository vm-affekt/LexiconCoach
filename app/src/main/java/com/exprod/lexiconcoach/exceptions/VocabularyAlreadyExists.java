package com.exprod.lexiconcoach.exceptions;

/**
 * Created by VLAD on 04.04.2017.
 */

public class VocabularyAlreadyExists extends RuntimeException {
    private String mVocabularyTitle;

    public VocabularyAlreadyExists(String vocabularyTitle) {
        mVocabularyTitle = vocabularyTitle;
    }

    public String getVocabularyTitle() {
        return mVocabularyTitle;
    }
}
