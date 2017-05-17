package com.exprod.lexiconcoach.storage.meta;

/**
 * Created by VLAD on 24.04.2017.
 */

public class VocabularyToWordMetaTable {
    public static final String TABLE_NAME = "vocabulary_to_word";

    public static final String VOCABULARY_ID_COLUMN = "VocabularyId";
    public static final String TRANSLATION_WORD_ID_COLUMN = "TranslationId";

    public static String getCreateTableQuery(){
        return "CREATE TABLE " + TABLE_NAME + " ("
                + VOCABULARY_ID_COLUMN + " INTEGER,"
                + TRANSLATION_WORD_ID_COLUMN + " INTEGER,"
                + "FOREIGN KEY (" + VOCABULARY_ID_COLUMN + ") REFERENCES " + VocabulariesMetaTable.TABLE_NAME + "(" + VocabulariesMetaTable.ID_COLUMN + "),"
                + "FOREIGN KEY (" + TRANSLATION_WORD_ID_COLUMN + ") REFERENCES " + WordTranslationsMetaTable.TABLE_NAME + "(" + WordTranslationsMetaTable.ID_COLUMN + ")"
                + "PRIMARY KEY (" + VOCABULARY_ID_COLUMN + ", " + TRANSLATION_WORD_ID_COLUMN + ")"
                + ");";
    }
}
