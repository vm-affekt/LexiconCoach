package com.exprod.lexiconcoach.storage.entities;

import android.support.annotation.Nullable;

import com.exprod.lexiconcoach.storage.meta.NativeWordsMetaTable;
import com.exprod.lexiconcoach.storage.meta.NativeWordsMetaTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by VLAD on 08.05.2017.
 */

@StorIOSQLiteType(table = NativeWordsMetaTable.TABLE_NAME)
public class NativeWordEntity {
    @Nullable
    @StorIOSQLiteColumn(name = NativeWordsMetaTable.ID_COLUMN, key = true)
    Long id;

    @StorIOSQLiteColumn(name = NativeWordsMetaTable.WORD_NAME_COLUMN)
    String wordName;

    NativeWordEntity(){
    }

    private NativeWordEntity(Long id, String wordName) {
        this.id = id;
        this.wordName = wordName;
    }

    public static NativeWordEntity newInstance(Long id, String wordName){
        return new NativeWordEntity(id, wordName);
    }

    public static NativeWordEntity newInstance(String wordName){
        return new NativeWordEntity(null, wordName);
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
