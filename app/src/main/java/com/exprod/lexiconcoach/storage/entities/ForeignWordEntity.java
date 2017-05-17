package com.exprod.lexiconcoach.storage.entities;

import android.support.annotation.Nullable;

import com.exprod.lexiconcoach.storage.meta.ForeignWordsMetaTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by VLAD on 08.05.2017.
 */

@StorIOSQLiteType(table = ForeignWordsMetaTable.TABLE_NAME)
public class ForeignWordEntity {
    @Nullable
    @StorIOSQLiteColumn(name = ForeignWordsMetaTable.ID_COLUMN, key = true)
    Long id;

    @StorIOSQLiteColumn(name = ForeignWordsMetaTable.WORD_NAME_COLUMN)
    String wordName;

    ForeignWordEntity(){
    }

    private ForeignWordEntity(Long id, String wordName) {
        this.id = id;
        this.wordName = wordName;
    }

    public static ForeignWordEntity newInstance(Long id, String wordName){
        return new ForeignWordEntity(id, wordName);
    }

    public static ForeignWordEntity newInstance(String wordName){
        return new ForeignWordEntity(null, wordName);
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }
}
