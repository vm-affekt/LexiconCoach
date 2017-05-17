package com.exprod.lexiconcoach.storage.entities;

import android.support.annotation.Nullable;

/**
 * Created by VLAD on 27.04.2017.
 */

public class WordTranslationSpecialEntity {
    @Nullable
    private Long translationId;

    private String foreignWord;
    private String nativeWord;

    @Nullable
    private Integer mistakeCount;

    private WordTranslationSpecialEntity(Long translationId, String foreignWord, String nativeWord, Integer mistakeCount) {
        this.translationId = translationId;
        this.foreignWord = foreignWord;
        this.nativeWord = nativeWord;
        this.mistakeCount = mistakeCount;
    }

    public static WordTranslationSpecialEntity newInstance(Long translationId, String foreignWord, String nativeWord, Integer mistakeCount){
        return new WordTranslationSpecialEntity(translationId, foreignWord, nativeWord, mistakeCount);
    }

    public static WordTranslationSpecialEntity newInstance(Long translationId, String foreignWord, String nativeWord){
        return new WordTranslationSpecialEntity(translationId, foreignWord, nativeWord, null);
    }

    public static WordTranslationSpecialEntity newInstance(String foreignWord, String nativeWord, Integer mistakeCount){
        return new WordTranslationSpecialEntity(null, foreignWord, nativeWord, mistakeCount);
    }

    @Nullable
    public Long getTranslationId() {
        return translationId;
    }

    public String getForeignWord() {
        return foreignWord;
    }

    public String getNativeWord() {
        return nativeWord;
    }

    @Nullable
    public Integer getMistakeCount() {
        return mistakeCount;
    }
}
