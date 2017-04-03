package com.exprod.lexiconcoach.storage.meta;

import android.util.Log;

import com.pushtorefresh.storio.sqlite.queries.Query;

/**
 * Created by VLAD on 26.03.2017.
 */

public class VocabulariesMetaTable {
    public static final String TABLE_NAME = "vocabularies";

    public static final String ID_COLUMN = "_id";

    public static final String TITLE_COLUMN = "title";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String CREATION_DATE_COLUMN = "creation_date";
    public static final String LAST_UPDATE_DATE_COLUMN = "last_update_date";

    public static final Query QUERY_ALL = Query.builder()
            .table(TABLE_NAME)
            .orderBy("datetime(" + LAST_UPDATE_DATE_COLUMN + ") desc")
            .orderBy("datetime(" + CREATION_DATE_COLUMN + ") desc")
            .build();

    public static String getCreateTableQuery(){
        return "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COLUMN + " INTEGER NOT NULL PRIMARY KEY,"
                + TITLE_COLUMN + " TEXT NOT NULL,"
                + DESCRIPTION_COLUMN + " TEXT NULL,"
                + CREATION_DATE_COLUMN + " TEXT NOT NULL,"
                + LAST_UPDATE_DATE_COLUMN + " TEXT NULL"
                + ");";
    }
}
