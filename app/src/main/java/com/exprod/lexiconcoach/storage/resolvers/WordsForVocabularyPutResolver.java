package com.exprod.lexiconcoach.storage.resolvers;

import android.support.annotation.NonNull;

import com.exprod.lexiconcoach.storage.entities.ForeignWordEntity;
import com.exprod.lexiconcoach.storage.entities.NativeWordEntity;
import com.exprod.lexiconcoach.storage.entities.WordTranslationEntity;
import com.exprod.lexiconcoach.storage.entities.WordTranslationSpecialEntity;
import com.exprod.lexiconcoach.storage.meta.ForeignWordsMetaTable;
import com.exprod.lexiconcoach.storage.meta.NativeWordsMetaTable;
import com.exprod.lexiconcoach.storage.meta.WordTranslationsMetaTable;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.put.PutResolver;
import com.pushtorefresh.storio.sqlite.operations.put.PutResult;
import com.pushtorefresh.storio.sqlite.queries.Query;
import com.pushtorefresh.storio.sqlite.queries.RawQuery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * Created by VLAD on 06.05.2017.
 */

public class WordsForVocabularyPutResolver extends PutResolver<WordTranslationSpecialEntity> {

    @NonNull
    @Override
    public PutResult performPut(@NonNull StorIOSQLite storIOSQLite, @NonNull WordTranslationSpecialEntity object) {
        Long translationId = object.getTranslationId();
        WordTranslationEntity wordTranslation;
        if (translationId != null) {
            wordTranslation = storIOSQLite.get()
                    .object(WordTranslationEntity.class)
                    .withQuery(
                            Query.builder()
                            .table(WordTranslationsMetaTable.TABLE_NAME)
                            .where(WordTranslationsMetaTable.ID_COLUMN + "=?")
                            .whereArgs(translationId)
                            .build()
                    )
                    .prepare()
                    .executeAsBlocking();
            ForeignWordEntity foreignWord =
                    storIOSQLite.get()
                    .object(ForeignWordEntity.class)
                    .withQuery(ForeignWordsMetaTable.buildQueryFindById(wordTranslation.getForeignWordId()))
                    .prepare()
                    .executeAsBlocking();
            NativeWordEntity nativeWord =
                    storIOSQLite.get()
                    .object(NativeWordEntity.class)
                    .withQuery(NativeWordsMetaTable.buildQueryFindById(wordTranslation.getNativeWordId()))
                    .prepare()
                    .executeAsBlocking();
            foreignWord.setWordName(object.getForeignWord());
            nativeWord.setWordName(object.getNativeWord());
            List<Object> wordsToUpdate = asList(foreignWord, nativeWord);
            storIOSQLite.put()
                    .objects(wordsToUpdate)
                    .prepare()
                    .executeAsBlocking();
            Set<String> affectedTables = new HashSet<>(2);
            affectedTables.add(ForeignWordsMetaTable.TABLE_NAME);
            affectedTables.add(NativeWordsMetaTable.TABLE_NAME);

            return PutResult.newUpdateResult(2, affectedTables);


        }else{
            Long foreignWordId = null;
            ForeignWordEntity foreignWord =
                    storIOSQLite.get()
                    .object(ForeignWordEntity.class)
                    .withQuery(ForeignWordsMetaTable.buildQueryFindByName(object.getForeignWord()))
                    .prepare()
                    .executeAsBlocking();
            if (foreignWord == null){
                foreignWord = ForeignWordEntity.newInstance(object.getForeignWord());
                foreignWordId = storIOSQLite.put()
                        .object(foreignWord)
                        .prepare()
                        .executeAsBlocking()
                        .insertedId();
            }else{
                foreignWordId = foreignWord.getId();
            }
            Long nativeWordId = null;
            NativeWordEntity nativeWord =
                    storIOSQLite.get()
                    .object(NativeWordEntity.class)
                    .withQuery(NativeWordsMetaTable.buildQueryFindByName(object.getNativeWord()))
                    .prepare()
                    .executeAsBlocking();
            if (nativeWord == null){
                nativeWord = NativeWordEntity.newInstance(object.getNativeWord());
                nativeWordId = storIOSQLite.put()
                        .object(nativeWord)
                        .prepare()
                        .executeAsBlocking()
                        .insertedId();
            }else{
                nativeWordId = nativeWord.getId();
            }
            wordTranslation = WordTranslationEntity.newInstance(foreignWordId, nativeWordId);

            translationId = storIOSQLite.put()
                    .object(wordTranslation)
                    .prepare()
                    .executeAsBlocking()
                    .insertedId();

            Set<String> affectedTables = new HashSet<>(3);
            affectedTables.add(ForeignWordsMetaTable.TABLE_NAME);
            affectedTables.add(NativeWordsMetaTable.TABLE_NAME);
            affectedTables.add(WordTranslationsMetaTable.TABLE_NAME);

            return PutResult.newInsertResult(translationId, affectedTables);
        }
    }
}
