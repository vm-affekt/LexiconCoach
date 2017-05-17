package com.exprod.lexiconcoach.storage.meta;

import com.pushtorefresh.storio.sqlite.queries.Query;

/**
 * Created by VLAD on 24.04.2017.
 */

public class NativeWordsMetaTable {
    public static final String TABLE_NAME = "native_words";

    public static final String ID_COLUMN = "_id";
    public static final String WORD_NAME_COLUMN = "WordName";

    public static Query buildQueryFindById(Long id){
        return Query.builder()
                .table(TABLE_NAME)
                .where(ID_COLUMN + "=?")
                .whereArgs(id)
                .build();
    }

    public static Query buildQueryFindByName(String wordName){
        return Query.builder()
                .table(TABLE_NAME)
                .where("lower(" + WORD_NAME_COLUMN + ")=?")
                .whereArgs(wordName.toLowerCase())
                .build();
    }

    public static String getCreateTableQuery(){
        return "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COLUMN + " INTEGER PRIMARY KEY,"
                + WORD_NAME_COLUMN + " TEXT NOT NULL"
                + ");";
    }
}
