package com.exprod.lexiconcoach.repositories;

import android.content.Context;

import com.exprod.lexiconcoach.LexiconCoachApp;
import com.exprod.lexiconcoach.storage.entities.RunResultEntity;
import com.exprod.lexiconcoach.storage.entities.VocabularyEntity;
import com.exprod.lexiconcoach.storage.meta.RunResultsMetaTable;
import com.exprod.lexiconcoach.storage.meta.VocabulariesMetaTable;
import com.exprod.lexiconcoach.viewmodels.PutVocabularyVM;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.queries.Query;

import java.util.List;

import javax.inject.Inject;

import rx.Completable;
import rx.Single;

/**
 * Created by VLAD on 02.04.2017.
 */

public class VocabularyRepositoryImpl implements VocabularyRepository {
    @Inject
    protected StorIOSQLite mStorIOSQLite;

    public VocabularyRepositoryImpl(Context context) {
        LexiconCoachApp.get(context).getAppComponent().injectInto(this);
    }

    @Override
    public Single<List<VocabularyEntity>> getAllVocabularies() {
        return mStorIOSQLite.get()
                .listOfObjects(VocabularyEntity.class)
                .withQuery(VocabulariesMetaTable.QUERY_ALL)
                .prepare()
                .asRxSingle();
    }

    @Override
    public Completable putVocabulary(VocabularyEntity vocabulary) {
        return mStorIOSQLite.put()
                .object(vocabulary)
                .prepare()
                .asRxCompletable();
    }

    @Override
    public Single<VocabularyEntity> getVocabulary(Long vocabularyId) {
        return mStorIOSQLite.get()
                .object(VocabularyEntity.class)
                .withQuery(Query.builder()
                        .table(VocabulariesMetaTable.TABLE_NAME)
                        .where(VocabulariesMetaTable.ID_COLUMN + "=?")
                        .whereArgs(vocabularyId)
                        .build()
                )
                .prepare()
                .asRxSingle();
    }

    @Override
    public Single<RunResultEntity> getLastRunResultFor(Long vocabularyId) {
        return mStorIOSQLite.get()
                .object(RunResultEntity.class)
                .withQuery(Query.builder()
                        .table(RunResultsMetaTable.TABLE_NAME)
                        .where(RunResultsMetaTable.VOCABULARY_ID_COLUMN + "=?")
                        .whereArgs(vocabularyId)
                        .orderBy(RunResultsMetaTable.ID_COLUMN + " DESC")
                        .limit(1)
                        .build())
                .prepare()
                .asRxSingle();
    }
}
