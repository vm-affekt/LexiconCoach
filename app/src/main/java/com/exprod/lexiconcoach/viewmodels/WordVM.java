package com.exprod.lexiconcoach.viewmodels;

import android.support.annotation.Nullable;

import com.exprod.lexiconcoach.storage.entities.WordTranslationSpecialEntity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by VLAD on 10.04.2017.
 */

public class WordVM {

    public static List<WordVM> getTestList(){
        return Arrays.asList(new WordVM((long)1, "bat", "летучая мышь"),
                            new WordVM((long)2, "bat", "бита"),
                            new WordVM((long)3, "table", "стол"),
                            new WordVM((long)4, "chair", "стул"),
                            new WordVM((long)5, "bottle", "бутылка")
        );
    }

    @Nullable
    private Long mTranslationId;

    private String mNativeWord;
    private String mTranslationWord;

    public WordVM() {
    }

    public WordVM(@Nullable Long translationId, String nativeWord, String translationWord) {
        this.mTranslationId = translationId;
        this.mNativeWord = nativeWord;
        this.mTranslationWord = translationWord;
    }

    public static WordVM mapFrom(WordTranslationSpecialEntity entity){
        return new WordVM(entity.getTranslationId(), entity.getNativeWord(), entity.getForeignWord());
    }

    @Nullable
    public Long getTranslationId() {
        return mTranslationId;
    }

    public void setTranslationId(@Nullable Long translationId) {
        mTranslationId = translationId;
    }

    public String getNativeWord() {
        return mNativeWord;
    }

    public void setNativeWord(String nativeWord) {
        this.mNativeWord = nativeWord;
    }

    public String getTranslationWord() {
        return mTranslationWord;
    }

    public void setTranslationWord(String translationWord) {
        this.mTranslationWord = translationWord;
    }

    @Override
    public String toString() {
        return "WordVM{" +
                "mTranslationId=" + mTranslationId +
                ", mNativeWord='" + mNativeWord + '\'' +
                ", mTranslationWord='" + mTranslationWord + '\'' +
                '}';
    }
}
