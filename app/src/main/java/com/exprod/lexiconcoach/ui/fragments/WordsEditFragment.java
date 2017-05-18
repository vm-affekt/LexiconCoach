package com.exprod.lexiconcoach.ui.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exprod.lexiconcoach.LexiconCoachApp;
import com.exprod.lexiconcoach.R;
import com.exprod.lexiconcoach.ui.adapters.WordsRecyclerViewAdapter;
import com.exprod.lexiconcoach.ui.dialogs.PutWordDialog;
import com.exprod.lexiconcoach.ui.presenters.WordsEditPresenter;
import com.exprod.lexiconcoach.ui.views.WordsEditView;
import com.exprod.lexiconcoach.viewmodels.WordVM;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class WordsEditFragment extends Fragment implements WordsEditView {
    public static final int LAYOUT = R.layout.fragment_words_edit;

    public static final String ARG_VOCABULARY_ID = "VOCABULARY_ID";

    private static final String LOG_TAG = "WORD_EDIT_FRAG";

    @Inject
    protected WordsEditPresenter mPresenter;

    @BindView(R.id.rvWords)
    protected RecyclerView mRvWords;

    private WordsRecyclerViewAdapter adapter;

    private Long mVocabularyId;

    private OnWordItemInteractionListener mWordClickListener;

    private final PutWordDialog.PutWordDialogInteractionListener mPutWordDialogInteractionListener = new PutWordDialog.PutWordDialogInteractionListener() {
        @Override
        public void onDismiss() {
            Log.d(LOG_TAG, "onDismiss()");
            requestWordList();
        }
    };

    public WordsEditFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LexiconCoachApp.get(getContext()).getAppComponent().injectInto(this);
        mPresenter.bindView(this);
        //mVocabularyId = savedInstanceState.getLong(ARG_VOCABULARY_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);

        mWordClickListener = new OnWordItemInteractionListener() {
            @Override
            public void onWordItemClick(Long translationId) {
                showAddEditDialog(translationId);
            }
        };
        adapter = new WordsRecyclerViewAdapter(new ArrayList<WordVM>(), mWordClickListener);
        ButterKnife.bind(this, view);


        mRvWords.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRvWords.addItemDecoration(new DividerItemDecoration(mRvWords.getContext(), LinearLayoutManager.VERTICAL)); // TODO проверить поддержку на платформах API<25
        mRvWords.setAdapter(adapter);
        requestWordList();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume() called...");
        //requestWordList();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause() called...");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop() called...");
    }

    @Override
    public Long getVocabularyId() {
        return mVocabularyId;
    }

    @Override
    public void setVocabularyId(Long vocabularyId) {
        Log.d(LOG_TAG, "setVocabularyId called with vocabularyId=" + vocabularyId);
        mVocabularyId = vocabularyId;
    }

    @Override
    public void requestWordList() {
        mPresenter.onWordListRequest();
    }

    @Override
    public void setWordList(List<WordVM> wordList) {
        Log.d(LOG_TAG, wordList.toString());
        adapter.setItems(wordList);
    }

    @Override
    public void onAddWordClicked() {
        showAddEditDialog(null);
    }

    private void showAddEditDialog(@Nullable Long translationId) {
        PutWordDialog dialog = PutWordDialog.newInstance(mVocabularyId, translationId);
        dialog.setInteractionListener(mPutWordDialogInteractionListener);
        dialog.show(getFragmentManager(), PutWordDialog.TAG);
    }

    public interface OnWordItemInteractionListener{
        void onWordItemClick(Long translationId);
    }
}
