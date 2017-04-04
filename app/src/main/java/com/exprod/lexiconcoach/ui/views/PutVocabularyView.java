package com.exprod.lexiconcoach.ui.views;

import com.exprod.lexiconcoach.viewmodels.PutVocabularyVM;

/**
 * Created by VLAD on 29.03.2017.
 */

public interface PutVocabularyView {
    void loadVocabularyData();
    void onSaveVocabulary();
    void onVocabularySaved();
    void setVocabularyId(Long vocabularyId);
    Long getVocabularyId();
    void setFields(PutVocabularyVM model);
    void showEmptyTitleError();
    void showVocabularyExistsError(String vocabularyTitle);
    String getTitleField();
    String getDescriptionField();

}
