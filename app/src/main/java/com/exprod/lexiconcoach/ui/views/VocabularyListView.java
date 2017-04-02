package com.exprod.lexiconcoach.ui.views;

import com.exprod.lexiconcoach.viewmodels.VocabularyItemVM;

import java.util.List;

/**
 * Created by VLAD on 26.03.2017.
 */

public interface VocabularyListView {
    void requestVocabularyList();
    void setList(List<VocabularyItemVM> list);
}
