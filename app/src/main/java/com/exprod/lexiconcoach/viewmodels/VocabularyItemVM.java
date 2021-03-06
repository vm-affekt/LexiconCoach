package com.exprod.lexiconcoach.viewmodels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by VLAD on 22.03.2017.
 */

public class VocabularyItemVM {

    public static List<VocabularyItemVM> getTestList(){
        return Arrays.asList(
            new VocabularyItemVM((long)1, "TOP 100 самых частых слов", "21.03.2017", 42, 100, 21, 4),
                new VocabularyItemVM((long)2, "Комната", "19.03.2017", 98, 32, 2, 4),
                new VocabularyItemVM((long)3, "Компьютер", "19.03.2017", 100, 43, 0, 1),
                new VocabularyItemVM((long)4, "Улица", "22.03.2017", 72, 42, 12, 4),
                new VocabularyItemVM((long)5, "Блюда", null, 52, 21, 12, 4),
                new VocabularyItemVM((long)6, "TOP 50 трудных слов (12.02.2017)", "12.03.2017", 52, 50, 12, 4)
        );
    }

    private Long mId;
    private String mTitle;
    private String mLastRunDateString;
    private Integer mCompletedPercent;
    private Integer mTotalWordsCount;
    private Integer mMistakesCount;
    private Integer mRunCount;

    public VocabularyItemVM() {
    }

    public VocabularyItemVM(Long id, String title, String lastRunDateString, Integer completedPercent, Integer totalWordsCount, Integer mistakesCount, Integer runCount) {
        mId = id;
        mTitle = title;
        mLastRunDateString = lastRunDateString;
        mCompletedPercent = completedPercent;
        mTotalWordsCount = totalWordsCount;
        mMistakesCount = mistakesCount;
        mRunCount = runCount;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getLastRunDateString() {
        return mLastRunDateString;
    }

    public void setLastRunDateString(String lastRunDateString) {
        mLastRunDateString = lastRunDateString;
    }

    public Integer getCompletedPercent() {
        return mCompletedPercent;
    }

    public void setCompletedPercent(Integer completedPercent) {
        mCompletedPercent = completedPercent;
    }

    public Integer getTotalWordsCount() {
        return mTotalWordsCount;
    }

    public void setTotalWordsCount(Integer totalWordsCount) {
        mTotalWordsCount = totalWordsCount;
    }

    public Integer getMistakesCount() {
        return mMistakesCount;
    }

    public void setMistakesCount(Integer mistakesCount) {
        mMistakesCount = mistakesCount;
    }

    public Integer getRunCount() {
        return mRunCount;
    }

    public void setRunCount(Integer runCount) {
        mRunCount = runCount;
    }

    @Override
    public String toString() {
        return "VocabularyItemVM{" +
                "mTitle='" + mTitle + '\'' +
                ", mLastRunDateString='" + mLastRunDateString + '\'' +
                ", mCompletedPercent='" + mCompletedPercent + '\'' +
                ", mTotalWordsCount=" + mTotalWordsCount +
                ", mMistakesCount=" + mMistakesCount +
                ", mRunCount=" + mRunCount +
                '}';
    }
}
