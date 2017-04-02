package com.exprod.lexiconcoach.mvpmodels;

import android.content.Context;

import com.exprod.lexiconcoach.LexiconCoachApp;
import com.exprod.lexiconcoach.repositories.VocabularyRepository;
import com.exprod.lexiconcoach.storage.entities.RunResultEntity;
import com.exprod.lexiconcoach.storage.entities.VocabularyEntity;
import com.exprod.lexiconcoach.storage.meta.RunResultsMetaTable;
import com.exprod.lexiconcoach.storage.meta.VocabulariesMetaTable;
import com.exprod.lexiconcoach.viewmodels.PutVocabularyVM;
import com.exprod.lexiconcoach.viewmodels.VocabularyItemVM;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.queries.Query;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import rx.Completable;
import rx.Observable;
import rx.Single;


/**
 * Created by VLAD on 27.03.2017.
 */

public class VocabularyMvpModelImpl implements VocabularyMvpModel {

    @Inject
    protected Calendar mCalendar;

    @Inject
    protected DateFormat mShortDateFormat;

    @Inject
    protected VocabularyRepository mRepository;


    private VocabularyEntity createVocabularyFrom(PutVocabularyVM model) {
        return VocabularyEntity.newInstance(model.getVocabularyId(), model.getTitle(), model.getDescription(), mShortDateFormat.format(mCalendar.getTime()), null);
    }

    public VocabularyMvpModelImpl(Context context) {
        LexiconCoachApp.get(context).getAppComponent().injectInto(this);
    }


    @Override
    public Observable<List<VocabularyItemVM>> getVocabularyItems() {
        return mRepository.getAllVocabularies()
                .flatMapObservable(lst -> Observable.from(lst))
                .flatMap(ve -> mRepository.getLastRunResultFor(ve.getId()).map(rre -> {
                    VocabularyItemVM item = new VocabularyItemVM();
                    item.setTitle(ve.getTitle());
                    item.setTotalWordsCount(90);
                    if (rre != null){
                        item.setCompletedPercent(rre.getTrueAnswersPercent());
                        item.setRunCount(10);
                        item.setMistakesCount(rre.getTotalMistakesCount());
                        item.setLastRunDateString(rre.getDateOfRun());
                    }else{
                        item.setCompletedPercent(0);
                        item.setRunCount(0);
                        item.setMistakesCount(0);
                        item.setLastRunDateString(null);
                    }
                    return item;
                }).toObservable())
                .toList();
    }

    @Override
    public Observable<PutVocabularyVM> getPutVocabularyVM(Long vocabularyId) {
        return mRepository.getVocabulary(vocabularyId)
                .map(ve -> new PutVocabularyVM(vocabularyId, ve.getTitle(), ve.getDescription()))
                .toObservable();
    }

    @Override
    public Completable putVocabulary(PutVocabularyVM model) {
        return mRepository.putVocabulary(createVocabularyFrom(model));
    }

}
