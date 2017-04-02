package com.exprod.lexiconcoach.storage;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.exprod.lexiconcoach.mvpmodels.VocabularyWithLastRunResultEntity;
import com.exprod.lexiconcoach.storage.entities.RunResultEntity;
import com.exprod.lexiconcoach.storage.entities.RunResultEntitySQLiteTypeMapping;
import com.exprod.lexiconcoach.storage.entities.VocabularyEntity;
import com.exprod.lexiconcoach.storage.entities.VocabularyEntitySQLiteTypeMapping;
import com.exprod.lexiconcoach.storage.resolvers.VocabularyWithLastRunResultGetResolver;
import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.get.GetResolver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

/**
 * Created by VLAD on 26.03.2017.
 */

@Module
public class DbModule {
    @Provides
    @NonNull
    @Singleton
    public StorIOSQLite provideStorIOSQLite(@NonNull SQLiteOpenHelper sqLiteOpenHelper) {
        VocabularyEntitySQLiteTypeMapping vocabularyEntitySQLiteTypeMapping = new VocabularyEntitySQLiteTypeMapping();
        GetResolver<VocabularyEntity> vocabularyEntityGetResolver = vocabularyEntitySQLiteTypeMapping.getResolver();


        return DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(sqLiteOpenHelper)
                .addTypeMapping(VocabularyEntity.class, vocabularyEntitySQLiteTypeMapping)
                .addTypeMapping(RunResultEntity.class, new RunResultEntitySQLiteTypeMapping())
//                .addTypeMapping(VocabularyWithLastRunResultEntity.class, SQLiteTypeMapping.<VocabularyWithLastRunResultEntity>builder()
//                        .putResolver(null)
//                        .getResolver(new VocabularyWithLastRunResultGetResolver(vocabularyEntityGetResolver))
//                        .deleteResolver(null)
//                        .build()
//                )
                .build();
    }

    @Provides
    @NonNull
    @Singleton
    public SQLiteOpenHelper provideSQLiteOpenHelper(@NonNull Context context) {
        return new DbOpenHelper(context);
    }
}
