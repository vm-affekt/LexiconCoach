package com.exprod.lexiconcoach.mvpmodels;

import android.content.Context;
import android.util.Log;

import com.exprod.lexiconcoach.DateUtilsModule;
import com.exprod.lexiconcoach.LexiconCoachApp;
import com.exprod.lexiconcoach.exceptions.VocabularyAlreadyExists;
import com.exprod.lexiconcoach.repositories.VocabularyRepository;
import com.exprod.lexiconcoach.storage.entities.VocabularyEntity;
import com.exprod.lexiconcoach.viewmodels.PutVocabularyVM;
import com.exprod.lexiconcoach.viewmodels.VocabularyItemVM;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Completable;
import rx.Observable;
import rx.Single;


/**
 * Created by VLAD on 27.03.2017.
 */

public class VocabularyMvpModelImpl implements VocabularyMvpModel {
    private static final String LOG_TAG = "VOC_MODEL_TAG";

    @Inject
    @Named(DateUtilsModule.ITEM_DATE_FORMAT_NAME)
    protected DateFormat mItemDateFormat;

    @Inject
    @Named(DateUtilsModule.SQLITE_DATE_TIME_FORMAT_NAME)
    protected DateFormat mSQLiteDateTimeFormat;

    @Inject
    protected VocabularyRepository mRepository;


    private VocabularyEntity createVocabularyFrom(PutVocabularyVM model) {
        Calendar calendar = Calendar.getInstance();
        return VocabularyEntity.newInstance(model.getVocabularyId(), model.getTitle(), model.getDescription(), mSQLiteDateTimeFormat.format(calendar.getTime()), null);
    }

    public VocabularyMvpModelImpl(Context context) {
        LexiconCoachApp.get(context).getAppComponent().injectInto(this);
    }


    @Override
    public Observable<List<VocabularyItemVM>> getVocabularyItems() {
        return mRepository.getAllVocabularies()
                .flatMapObservable(lst -> Observable.from(lst))
                .concatMap(ve -> mRepository.getLastRunResultFor(ve.getId()).map(rre -> {
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
    public Completable addVocabulary(PutVocabularyVM model) {
        return mRepository.isVocabularyExists(model.getTitle())
                .flatMap(b -> !b ? Single.just(b) : Single.error(new VocabularyAlreadyExists(model.getTitle()))) // TODO По возможности найти другое решение выбросить исключение здесь...
                .flatMapCompletable(___ -> mRepository.putVocabulary(createVocabularyFrom(model)));
    }

    @Override
    public Completable editVocabulary(PutVocabularyVM model) {
        return mRepository.putVocabulary(createVocabularyFrom(model));
    }
}
