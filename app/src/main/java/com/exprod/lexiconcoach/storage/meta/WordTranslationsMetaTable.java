package com.exprod.lexiconcoach.storage.meta;

import com.exprod.lexiconcoach.storage.resolvers.WordsForVocabularyGetResolver;
import com.pushtorefresh.storio.sqlite.queries.Query;
import com.pushtorefresh.storio.sqlite.queries.RawQuery;

/**
 * Created by VLAD on 24.04.2017.
 */

public class WordTranslationsMetaTable {
    public static final String TABLE_NAME = "word_translations";

    public static final String ID_COLUMN = "TranslationId";
    public static final String FOREIGN_WORD_ID_COLUMN = "ForeignWordId";
    public static final String NATIVE_WORD_ID_COLUMN = "NativeWordId";

    public static final String MISTAKE_COUNT_COLUMN = "MistakeCount";

    private static Query buildQueryGetTranslations(Long wordId, String wordColumnName){
        return Query.builder()
                .table(TABLE_NAME)
                .where(wordColumnName + "=?")
                .whereArgs(wordId)
                .build();
    }

    public static RawQuery buildQueryAllForVocabulary(Long vocabularyId){
        return RawQuery.builder()
                .query(WordsForVocabularyGetResolver.TEXT_QUERY_ALL_FOR_VOCABULARY)
                .args(vocabularyId)
                .build();
    }

    public static RawQuery buildQueryGetById(Long translationId){
        return RawQuery.builder()
                .query(WordsForVocabularyGetResolver.TEXT_QUERY_GET_BY_ID)
                .args(translationId)
                .build();
    }

    public static Query buildQueryGetCountOfForeignWord(Long foreignWordId){
        return buildQueryGetTranslations(foreignWordId, FOREIGN_WORD_ID_COLUMN);
    }

    public static Query buildQueryGetCountOfNativeWord(Long nativeWordId){
        return buildQueryGetTranslations(nativeWordId, NATIVE_WORD_ID_COLUMN);
    }

    public static String getCreateTableQuery(){
        return "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COLUMN + " INTEGER PRIMARY KEY,"
                + FOREIGN_WORD_ID_COLUMN + " INTEGER NOT NULL,"
                + NATIVE_WORD_ID_COLUMN + " INTEGER NOT NULL,"
                + MISTAKE_COUNT_COLUMN + " INTEGER,"
                + "FOREIGN KEY (" + FOREIGN_WORD_ID_COLUMN + ") REFERENCES " + ForeignWordsMetaTable.TABLE_NAME + "(" + ForeignWordsMetaTable.ID_COLUMN + "),"
                + "FOREIGN KEY (" + NATIVE_WORD_ID_COLUMN + ") REFERENCES " + NativeWordsMetaTable.TABLE_NAME + "(" + NativeWordsMetaTable.ID_COLUMN + ")"
                + ");";

    }


}
