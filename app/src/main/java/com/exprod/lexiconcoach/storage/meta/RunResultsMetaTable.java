package com.exprod.lexiconcoach.storage.meta;

import com.pushtorefresh.storio.sqlite.queries.Query;

/**
 * Created by VLAD on 26.03.2017.
 */

public class RunResultsMetaTable {
    public static final String TABLE_NAME = "run_results";

    public static final String ID_COLUMN = "_id";
    public static final String VOCABULARY_ID_COLUMN = "vocabulary_id";
    public static final String TRUE_ANSWERS_PERCENT_COLUMN = "true_answers_percent";
    public static final String DATE_OF_RUN_COLUMN = "date_of_run";
    //public static final String IS_INTERRUPTED = "is_interrupted";
    public static final String TOTAL_MISTAKES_COUNT = "total_mistakes_count";

    //public static final Query QUERY_LAST_BY_VOCABULARY =


    public static String getCreateTableQuery(){
        return "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COLUMN + " INTEGER NOT NULL PRIMARY KEY,"
                + VOCABULARY_ID_COLUMN + " INTEGER NOT NULL,"
                + TRUE_ANSWERS_PERCENT_COLUMN + " INTEGER NOT NULL,"
                + DATE_OF_RUN_COLUMN + " TEXT NOT NULL,"
                + TOTAL_MISTAKES_COUNT + " INTEGER NOT NULL,"
                + "FOREIGN KEY(" + VOCABULARY_ID_COLUMN + ")"
                + " REFERENCES " + VocabulariesMetaTable.TABLE_NAME + " (" + VocabulariesMetaTable.ID_COLUMN + ")"
                + ");";

    }

}
