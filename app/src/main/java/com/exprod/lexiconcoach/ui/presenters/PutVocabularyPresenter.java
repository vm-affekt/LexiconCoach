package com.exprod.lexiconcoach.ui.presenters;

import android.content.Context;
import android.os.Bundle;

import com.exprod.lexiconcoach.LexiconCoachApp;
import com.exprod.lexiconcoach.mvpmodels.VocabularyMvpModel;
import com.exprod.lexiconcoach.ui.views.PutVocabularyView;
import com.exprod.lexiconcoach.viewmodels.PutVocabularyVM;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by VLAD on 30.03.2017.
 */

public class PutVocabularyPresenter extends BasePresenter<PutVocabularyView> {

    @Inject
    protected VocabularyMvpModel mVocabularyMvpModel;


    private PutVocabularyVM makeViewModel(){
        return new PutVocabularyVM(
                getView().getVocabularyId(),
                getView().getTitleField().trim(),
                getView().getDescriptionField()
        );
    }

    public PutVocabularyPresenter(Context context) {
        LexiconCoachApp.get(context).getAppComponent().injectInto(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    public void onLoadDataFor(Long vocabularyId){
        if(isViewNotNull()){
            Subscription subscription = mVocabularyMvpModel.getPutVocabularyVM(vocabularyId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(vm -> {
                        getView().setFields(vm);
                    });
            addSubscription(subscription);
        }
    }

    public void onSaveVocabulary(){
        if (isViewNotNull()){
            PutVocabularyVM model = makeViewModel();
            if (model.getTitle().isEmpty()) {
                getView().showEmptyTitleError();
                return;
            }
            mVocabularyMvpModel.putVocabulary(makeViewModel())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        getView().onVocabularySaved();
                    });


        }
    }


}
