package com.exprod.lexiconcoach.storage.entities;

import com.exprod.lexiconcoach.storage.meta.VocabularyToWordMetaTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by VLAD on 08.05.2017.
 */

@StorIOSQLiteType(table = VocabularyToWordMetaTable.TABLE_NAME)
public class VocabularyToWordEntity {

    @StorIOSQLiteColumn(name = VocabularyToWordMetaTable.VOCABULARY_ID_COLUMN, key = true)
    Long vocabularyId;
    @StorIOSQLiteColumn(name = VocabularyToWordMetaTable.TRANSLATION_WORD_ID_COLUMN, key = true)
    Long translationId;

    VocabularyToWordEntity(){

    }

    private VocabularyToWordEntity(Long vocabularyId, Long translationId) {
        this.vocabularyId = vocabularyId;
        this.translationId = translationId;
    }

    public static VocabularyToWordEntity newInstance(Long vocabularyId, Long translationId){
        return new VocabularyToWordEntity(vocabularyId, translationId);
    }

    public Long getVocabularyId() {
        return vocabularyId;
    }

    public void setVocabularyId(Long vocabularyId) {
        this.vocabularyId = vocabularyId;
    }

    public Long getTranslationId() {
        return translationId;
    }

    public void setTranslationId(Long translationId) {
        this.translationId = translationId;
    }
}
