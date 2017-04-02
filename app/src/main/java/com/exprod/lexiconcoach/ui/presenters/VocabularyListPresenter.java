package com.exprod.lexiconcoach.ui.presenters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.exprod.lexiconcoach.LexiconCoachApp;
import com.exprod.lexiconcoach.mappers.VocabularyItemViewModelMapper;
import com.exprod.lexiconcoach.mvpmodels.VocabularyMvpModel;
import com.exprod.lexiconcoach.storage.entities.RunResultEntity;
import com.exprod.lexiconcoach.ui.views.VocabularyListView;
import com.exprod.lexiconcoach.viewmodels.VocabularyItemVM;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Single;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by VLAD on 26.03.2017.
 */

public class VocabularyListPresenter extends BasePresenter<VocabularyListView>  {
    @Inject
    protected VocabularyMvpModel model;


    public VocabularyListPresenter(Context context) {
        LexiconCoachApp.get(context).getAppComponent().injectInto(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    public void onVocabularyListRequest() {
        Log.d("VOC_LOG", "onVocabularyListRequest()");
        if (isViewNotNull()) {
            Subscription subscription = model.getVocabularyItems()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(lst -> {
                       getView().setList(lst);
                    });
            addSubscription(subscription);
        }
    }
}
