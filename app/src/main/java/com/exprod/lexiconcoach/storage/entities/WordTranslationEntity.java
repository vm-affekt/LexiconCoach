package com.exprod.lexiconcoach.storage.entities;

import android.support.annotation.Nullable;

import com.exprod.lexiconcoach.storage.meta.WordTranslationsMetaTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by VLAD on 08.05.2017.
 */

@StorIOSQLiteType(table = WordTranslationsMetaTable.TABLE_NAME)
public class WordTranslationEntity {

    @Nullable
    @StorIOSQLiteColumn(name = WordTranslationsMetaTable.ID_COLUMN, key = true)
    Long translationId;

    @StorIOSQLiteColumn(name = WordTranslationsMetaTable.FOREIGN_WORD_ID_COLUMN)
    Long foreignWordId;

    @StorIOSQLiteColumn(name = WordTranslationsMetaTable.NATIVE_WORD_ID_COLUMN)
    Long nativeWordId;

    @Nullable
    @StorIOSQLiteColumn(name = WordTranslationsMetaTable.MISTAKE_COUNT_COLUMN)
    Integer mistakeCount;

    WordTranslationEntity(){

    }

    public WordTranslationEntity(Long translationId, Long foreignWordId, Long nativeWordId, Integer mistakeCount) {
        this.translationId = translationId;
        this.foreignWordId = foreignWordId;
        this.nativeWordId = nativeWordId;
        this.mistakeCount = mistakeCount;
    }

    public static WordTranslationEntity newInstance(Long translationId, Long foreignWordId, Long nativeWordId, Integer mistakeCount){
        return new WordTranslationEntity(translationId, foreignWordId, nativeWordId, mistakeCount);
    }

    public static WordTranslationEntity newInstance(Long foreignWordId, Long nativeWordId, Integer mistakeCount){
        return new WordTranslationEntity(null, foreignWordId, nativeWordId, mistakeCount);
    }

    public static WordTranslationEntity newInstance(Long foreignWordId, Long nativeWordId){
        return new WordTranslationEntity(null, foreignWordId, nativeWordId, null);
    }

    @Nullable
    public Long getTranslationId() {
        return translationId;
    }

    public void setTranslationId(@Nullable Long translationId) {
        this.translationId = translationId;
    }

    public Long getForeignWordId() {
        return foreignWordId;
    }

    public void setForeignWordId(Long foreignWordId) {
        this.foreignWordId = foreignWordId;
    }

    public Long getNativeWordId() {
        return nativeWordId;
    }

    public void setNativeWordId(Long nativeWordId) {
        this.nativeWordId = nativeWordId;
    }

    @Nullable
    public Integer getMistakeCount() {
        return mistakeCount;
    }

    public void setMistakeCount(@Nullable Integer mistakeCount) {
        this.mistakeCount = mistakeCount;
    }
}
