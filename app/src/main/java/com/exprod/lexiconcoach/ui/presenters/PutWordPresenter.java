package com.exprod.lexiconcoach.ui.presenters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.exprod.lexiconcoach.LexiconCoachApp;
import com.exprod.lexiconcoach.mvpmodels.WordMvpModel;
import com.exprod.lexiconcoach.ui.views.PutWordView;
import com.exprod.lexiconcoach.viewmodels.WordVM;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

/**
 * Created by VLAD on 09.05.2017.
 */

public class PutWordPresenter extends BasePresenter<PutWordView> {
    private static final String LOG_TAG = "PUT_WORD_PRESENTER";

    @Inject
    protected WordMvpModel mWordMvpModel;

    private boolean checkValidFields(){
        String foreignWord = getView().getForeignWord();
        String nativeWord = getView().getNativeWord();
        return !(foreignWord.isEmpty() || nativeWord.isEmpty());
    }

    private WordVM makeViewModel(){
        return new WordVM(
                getView().getTranslationId(),
                getView().getNativeWord(),
                getView().getForeignWord()
        );
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (getView().isEditMode()){
            mWordMvpModel.getWordById(getView().getTranslationId())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(w -> {
                        getView().setForeignWord(w.getTranslationWord());
                        getView().setNativeWord(w.getNativeWord());
                    });
        }
    }

    public PutWordPresenter(Context context){
        LexiconCoachApp.get(context).getAppComponent().injectInto(this);
    }

    public void onSaveWord(){
        if (!checkValidFields()){
            getView().showEmptyFieldsError();
        }else{
            mWordMvpModel.putWordTranslation(getView().getVocabularyId(), makeViewModel())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        Log.d(LOG_TAG, "putWordTranslation onComplete() called..!!");
                        getView().onWordSaved();
                    });
        }
    }
}
