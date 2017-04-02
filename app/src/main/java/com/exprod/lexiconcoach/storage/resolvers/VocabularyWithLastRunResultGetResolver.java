package com.exprod.lexiconcoach.storage.resolvers;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.exprod.lexiconcoach.mvpmodels.VocabularyWithLastRunResultEntity;
import com.exprod.lexiconcoach.storage.entities.RunResultEntity;
import com.exprod.lexiconcoach.storage.entities.VocabularyEntity;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.get.GetResolver;
import com.pushtorefresh.storio.sqlite.queries.Query;
import com.pushtorefresh.storio.sqlite.queries.RawQuery;

import static com.exprod.lexiconcoach.storage.meta.RunResultsMetaTable.ID_COLUMN;
import static com.exprod.lexiconcoach.storage.meta.RunResultsMetaTable.TABLE_NAME;
import static com.exprod.lexiconcoach.storage.meta.RunResultsMetaTable.VOCABULARY_ID_COLUMN;

/**
 * Created by VLAD on 27.03.2017.
 */

public class VocabularyWithLastRunResultGetResolver extends GetResolver<VocabularyWithLastRunResultEntity> {

    @NonNull
    private final GetResolver<VocabularyEntity> mVocabularyGetResolver;

    @NonNull
    private final ThreadLocal<StorIOSQLite> mStorIOSQLiteFromPerformGet = new ThreadLocal<StorIOSQLite>();

    public VocabularyWithLastRunResultGetResolver(@NonNull GetResolver<VocabularyEntity> vocabularyGetResolver) {
        mVocabularyGetResolver = vocabularyGetResolver;
    }

    @NonNull
    @Override
    public VocabularyWithLastRunResultEntity mapFromCursor(@NonNull Cursor cursor) {
        final VocabularyEntity vocabulary = mVocabularyGetResolver.mapFromCursor(cursor);
        final RunResultEntity runResultEntity = mStorIOSQLiteFromPerformGet.get()
                .get()
                .object(RunResultEntity.class)
                .withQuery(Query
                        .builder()
                        .table(TABLE_NAME)
                        .where(VOCABULARY_ID_COLUMN + "=?")
                        .whereArgs(vocabulary.getId())
                        .orderBy(ID_COLUMN + " DESC")
                        .limit(1)
                        .build())
                .prepare()
                .executeAsBlocking();
        return VocabularyWithLastRunResultEntity.newInstance(vocabulary, runResultEntity);
    }

    @NonNull
    @Override
    public Cursor performGet(@NonNull StorIOSQLite storIOSQLite, @NonNull RawQuery rawQuery) {
        mStorIOSQLiteFromPerformGet.set(storIOSQLite);
        return mStorIOSQLiteFromPerformGet.get().lowLevel().rawQuery(rawQuery);
    }

    @NonNull
    @Override
    public Cursor performGet(@NonNull StorIOSQLite storIOSQLite, @NonNull Query query) {
        mStorIOSQLiteFromPerformGet.set(storIOSQLite);
        return mStorIOSQLiteFromPerformGet.get().lowLevel().query(query);
    }
}
