package com.exprod.lexiconcoach.storage.entities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.exprod.lexiconcoach.storage.meta.RunResultsMetaTable;
import com.exprod.lexiconcoach.storage.meta.VocabulariesMetaTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by VLAD on 26.03.2017.
 */

@StorIOSQLiteType(table = RunResultsMetaTable.TABLE_NAME)
public class RunResultEntity {
    @Nullable
    @StorIOSQLiteColumn(name = RunResultsMetaTable.ID_COLUMN, key = true)
    Long id;

    @NonNull
    @StorIOSQLiteColumn(name = RunResultsMetaTable.VOCABULARY_ID_COLUMN)
    Long vocabularyId;

    @NonNull
    @StorIOSQLiteColumn(name = RunResultsMetaTable.TRUE_ANSWERS_PERCENT_COLUMN)
    Integer trueAnswersPercent;

    @NonNull
    @StorIOSQLiteColumn(name = RunResultsMetaTable.DATE_OF_RUN_COLUMN)
    String dateOfRun;

    @NonNull
    @StorIOSQLiteColumn(name = RunResultsMetaTable.TOTAL_MISTAKES_COUNT)
    Integer totalMistakesCount;

    RunResultEntity(){

    }

    private RunResultEntity(Long id, @NonNull Long vocabularyId, @NonNull Integer trueAnswersPercent, @NonNull String dateOfRun, @NonNull Integer totalMistakesCount) {
        this.id = id;
        this.vocabularyId = vocabularyId;
        this.trueAnswersPercent = trueAnswersPercent;
        this.dateOfRun = dateOfRun;
        this.totalMistakesCount = totalMistakesCount;
    }

    public static RunResultEntity newInstance (Long id, @NonNull Long vocabularyId, @NonNull Integer trueAnswersPercent, @NonNull String dateOfRun, @NonNull Integer totalMistakesCount){
        return new RunResultEntity(id, vocabularyId, trueAnswersPercent, dateOfRun, totalMistakesCount);
    }
    public static RunResultEntity newInstance (@NonNull Long vocabularyId, @NonNull Integer trueAnswersPercent, @NonNull String dateOfRun, @NonNull Integer totalMistakesCount){
        return newInstance(null, vocabularyId, trueAnswersPercent, dateOfRun, totalMistakesCount);
    }

    @Nullable
    public Long getId() {
        return id;
    }

    @NonNull
    public Long getVocabularyId() {
        return vocabularyId;
    }

    @NonNull
    public Integer getTrueAnswersPercent() {
        return trueAnswersPercent;
    }

    @NonNull
    public String getDateOfRun() {
        return dateOfRun;
    }

    @NonNull
    public Integer getTotalMistakesCount() {
        return totalMistakesCount;
    }
}
