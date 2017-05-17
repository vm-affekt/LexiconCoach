package com.exprod.lexiconcoach.ui.views;

import com.exprod.lexiconcoach.viewmodels.WordVM;

import java.util.List;

/**
 * Created by VLAD on 24.04.2017.
 */

public interface WordsEditView {
    Long getVocabularyId();
    void setVocabularyId(Long vocabularyId);
    void requestWordList();
    void setWordList(List<WordVM> wordList);
    void onAddWordClicked();
}
