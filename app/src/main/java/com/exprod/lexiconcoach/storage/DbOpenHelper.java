package com.exprod.lexiconcoach.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.exprod.lexiconcoach.storage.meta.ForeignWordsMetaTable;
import com.exprod.lexiconcoach.storage.meta.NativeWordsMetaTable;
import com.exprod.lexiconcoach.storage.meta.RunResultsMetaTable;
import com.exprod.lexiconcoach.storage.meta.VocabulariesMetaTable;
import com.exprod.lexiconcoach.storage.meta.VocabularyToWordMetaTable;
import com.exprod.lexiconcoach.storage.meta.WordTranslationsMetaTable;

/**
 * Created by VLAD on 26.03.2017.
 */

public class DbOpenHelper extends SQLiteOpenHelper {

    public DbOpenHelper(@NonNull Context context) {
        super(context, "lexicon_coach_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(VocabulariesMetaTable.getCreateTableQuery());
        sqLiteDatabase.execSQL(RunResultsMetaTable.getCreateTableQuery());

        sqLiteDatabase.execSQL(ForeignWordsMetaTable.getCreateTableQuery());
        sqLiteDatabase.execSQL(NativeWordsMetaTable.getCreateTableQuery());
        sqLiteDatabase.execSQL(WordTranslationsMetaTable.getCreateTableQuery());

        sqLiteDatabase.execSQL(VocabularyToWordMetaTable.getCreateTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
