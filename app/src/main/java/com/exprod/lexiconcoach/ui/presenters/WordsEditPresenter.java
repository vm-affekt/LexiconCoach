package com.exprod.lexiconcoach.ui.presenters;

import android.content.Context;
import android.os.Bundle;

import com.exprod.lexiconcoach.LexiconCoachApp;
import com.exprod.lexiconcoach.mvpmodels.WordMvpModel;
import com.exprod.lexiconcoach.ui.views.VocabularyListView;
import com.exprod.lexiconcoach.ui.views.WordsEditView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by VLAD on 24.04.2017.
 */

public class WordsEditPresenter extends BasePresenter<WordsEditView>{
    @Inject
    protected WordMvpModel mWordMvpModel;

    public WordsEditPresenter(Context context){
        LexiconCoachApp.get(context).getAppComponent().injectInto(this);
    }

    public void onWordListRequest(){
        Long vocabularyId = getView().getVocabularyId();
        mWordMvpModel.getAllWordsForVocabulary(vocabularyId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lst -> getView().setWordList(lst));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }
}
