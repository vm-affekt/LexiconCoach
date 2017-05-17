package com.exprod.lexiconcoach.storage.resolvers;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.exprod.lexiconcoach.storage.entities.WordTranslationSpecialEntity;
import com.exprod.lexiconcoach.storage.meta.ForeignWordsMetaTable;
import com.exprod.lexiconcoach.storage.meta.NativeWordsMetaTable;
import com.exprod.lexiconcoach.storage.meta.VocabulariesMetaTable;
import com.exprod.lexiconcoach.storage.meta.VocabularyToWordMetaTable;
import com.exprod.lexiconcoach.storage.meta.WordTranslationsMetaTable;
import com.pushtorefresh.storio.sqlite.operations.get.DefaultGetResolver;

/**
 * Created by VLAD on 05.05.2017.
 */

public class WordsForVocabularyGetResolver extends DefaultGetResolver<WordTranslationSpecialEntity> {
    private static final String FOREIGN_WORD_ALIAS = "ForeignWord";
    private static final String NATIVE_WORD_ALIAS = "NativeWord";

    private static final String TEXT_BASE_QUERY =
            "SELECT "
            + "WT." + WordTranslationsMetaTable.ID_COLUMN
            + ",FW." + ForeignWordsMetaTable.WORD_NAME_COLUMN + " AS " + FOREIGN_WORD_ALIAS
            + ",NW." + NativeWordsMetaTable.WORD_NAME_COLUMN + " AS " + NATIVE_WORD_ALIAS
            + ",WT." + WordTranslationsMetaTable.MISTAKE_COUNT_COLUMN
            + " FROM "
            + VocabulariesMetaTable.TABLE_NAME + " V "
            + "INNER JOIN " + VocabularyToWordMetaTable.TABLE_NAME + " VTW ON V." + VocabulariesMetaTable.ID_COLUMN + "=VTW." + VocabularyToWordMetaTable.VOCABULARY_ID_COLUMN
            + " INNER JOIN " + WordTranslationsMetaTable.TABLE_NAME + " WT ON VTW." + VocabularyToWordMetaTable.TRANSLATION_WORD_ID_COLUMN + "=WT." + WordTranslationsMetaTable.ID_COLUMN
            + " INNER JOIN " + ForeignWordsMetaTable.TABLE_NAME + " FW ON WT." + WordTranslationsMetaTable.FOREIGN_WORD_ID_COLUMN + "=FW." + ForeignWordsMetaTable.ID_COLUMN
            + " INNER JOIN " + NativeWordsMetaTable.TABLE_NAME + " NW ON WT." + WordTranslationsMetaTable.NATIVE_WORD_ID_COLUMN + "=NW." + NativeWordsMetaTable.ID_COLUMN;

    public static final String TEXT_QUERY_ALL_FOR_VOCABULARY =
                    TEXT_BASE_QUERY
                    + " WHERE "
                    + "V." + VocabulariesMetaTable.ID_COLUMN + "=?";

    public static final String TEXT_QUERY_GET_BY_ID =
                    TEXT_BASE_QUERY
                    + " WHERE "
                    + "WT." + WordTranslationsMetaTable.ID_COLUMN + "=?";


    @NonNull
    @Override
    public WordTranslationSpecialEntity mapFromCursor(@NonNull Cursor cursor) {
        Long translationId = cursor.getLong(cursor.getColumnIndex(WordTranslationsMetaTable.ID_COLUMN));
        String foreignWord = cursor.getString(cursor.getColumnIndex(FOREIGN_WORD_ALIAS));
        String nativeWord = cursor.getString(cursor.getColumnIndex(NATIVE_WORD_ALIAS));
        Integer mistakeCount = cursor.getInt(cursor.getColumnIndex(WordTranslationsMetaTable.MISTAKE_COUNT_COLUMN));
        return WordTranslationSpecialEntity.newInstance(translationId, foreignWord, nativeWord, mistakeCount);
    }
}
