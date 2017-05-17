package com.exprod.lexiconcoach.repositories;

import android.content.Context;
import android.util.Log;

import com.exprod.lexiconcoach.LexiconCoachApp;
import com.exprod.lexiconcoach.storage.entities.VocabularyToWordEntity;
import com.exprod.lexiconcoach.storage.entities.WordTranslationSpecialEntity;
import com.exprod.lexiconcoach.storage.meta.WordTranslationsMetaTable;
import com.exprod.lexiconcoach.storage.resolvers.WordsForVocabularyGetResolver;
import com.exprod.lexiconcoach.storage.resolvers.WordsForVocabularyPutResolver;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.put.PutResult;

import java.util.List;

import javax.inject.Inject;

import rx.Completable;
import rx.Single;

/**
 * Created by VLAD on 05.05.2017.
 */

public class WordRepositoryImpl implements WordRepository {
    private static final String LOG_TAG = "WORD_REPO";

    @Inject
    protected StorIOSQLite mStorIOSQLite;

    public WordRepositoryImpl(Context context) {
        LexiconCoachApp.get(context).getAppComponent().injectInto(this);
    }

    // TODO Убрать постоянное создавание нового GetResolver'а
    @Override
    public Single<List<WordTranslationSpecialEntity>> getWordsForVocabulary(Long vocabularyId) {
        return mStorIOSQLite.get()
                .listOfObjects(WordTranslationSpecialEntity.class)
                .withQuery(WordTranslationsMetaTable.buildQueryAllForVocabulary(vocabularyId))
                .withGetResolver(new WordsForVocabularyGetResolver())
                .prepare()
                .asRxSingle();
    }

    @Override
    public Single<WordTranslationSpecialEntity> getWordById(Long translationId) {
        return mStorIOSQLite.get()
                .object(WordTranslationSpecialEntity.class)
                .withQuery(WordTranslationsMetaTable.buildQueryGetById(translationId))
                .withGetResolver(new WordsForVocabularyGetResolver())
                .prepare()
                .asRxSingle();

    }

    @Override
    public Single<Long> putWordTranslation(WordTranslationSpecialEntity wordTranslation) {
        return mStorIOSQLite.put()
                .object(wordTranslation)
                .withPutResolver(new WordsForVocabularyPutResolver())
                .prepare()
                .asRxSingle()
                .map(PutResult::insertedId);
    }

    @Override
    public Completable attachTranslationToVocabulary(Long vocabularyId, Long translationId) {
        Log.d(LOG_TAG, "attachTranslationToVocabulary called for vocabularyId=" + vocabularyId + " and translationId=" + translationId);
        VocabularyToWordEntity vocabularyToWord = VocabularyToWordEntity.newInstance(vocabularyId, translationId);
        return mStorIOSQLite.put()
                .object(vocabularyToWord)
                .prepare()
                .asRxCompletable();
    }
}
