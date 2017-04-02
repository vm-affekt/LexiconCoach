package com.exprod.lexiconcoach.storage.entities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.exprod.lexiconcoach.storage.meta.VocabulariesMetaTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by VLAD on 26.03.2017.
 */

@StorIOSQLiteType(table = VocabulariesMetaTable.TABLE_NAME)
public class VocabularyEntity {
    @Nullable
    @StorIOSQLiteColumn(name = VocabulariesMetaTable.ID_COLUMN, key = true)
    Long id;

    @NonNull
    @StorIOSQLiteColumn(name = VocabulariesMetaTable.TITLE_COLUMN)
    String title;

    @NonNull
    @StorIOSQLiteColumn(name = VocabulariesMetaTable.DESCRIPTION_COLUMN)
    String description;

    @NonNull
    @StorIOSQLiteColumn( name = VocabulariesMetaTable.CREATION_DATE_COLUMN)
    String creationDate;

    @Nullable
    @StorIOSQLiteColumn( name = VocabulariesMetaTable.LAST_UPDATE_DATE_COLUMN)
    String lastUpdateDate;

    VocabularyEntity(){

    }

    private VocabularyEntity(Long id, @NonNull String title, @NonNull String description, @NonNull String creationDate, String lastUpdateDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public static VocabularyEntity newInstance (Long id, @NonNull String title, @NonNull String description, @NonNull String creationDate, String lastUpdateDate){
        return new VocabularyEntity(id, title, description, creationDate, lastUpdateDate);
    }
    public static VocabularyEntity newInstance (@NonNull String title, @NonNull String description, @NonNull String creationDate, String lastUpdateDate){
        return newInstance(null, title, description, creationDate, lastUpdateDate);
    }

    @Nullable
    public Long getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public String getCreationDate() {
        return creationDate;
    }

    @Nullable
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }
}
