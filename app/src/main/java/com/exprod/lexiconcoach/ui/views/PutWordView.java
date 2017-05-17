package com.exprod.lexiconcoach.ui.views;

/**
 * Created by VLAD on 09.05.2017.
 */

public interface PutWordView {
    String getForeignWord();
    String getNativeWord();
    void setForeignWord(String foreignWord);
    void setNativeWord(String nativeWord);
    Long getVocabularyId();
    Boolean isEditMode();
    Long getTranslationId();
    void showEmptyFieldsError();
    void onWordSaved();
}
