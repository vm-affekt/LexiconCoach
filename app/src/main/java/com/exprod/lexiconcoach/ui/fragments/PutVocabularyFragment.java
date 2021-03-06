package com.exprod.lexiconcoach.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.exprod.lexiconcoach.LexiconCoachApp;
import com.exprod.lexiconcoach.R;
import com.exprod.lexiconcoach.ui.presenters.PutVocabularyPresenter;
import com.exprod.lexiconcoach.ui.views.PutVocabularyView;
import com.exprod.lexiconcoach.viewmodels.PutVocabularyVM;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by VLAD on 29.03.2017.
 */

public class PutVocabularyFragment extends Fragment implements PutVocabularyView {
    public static final int LAYOUT = R.layout.fragment_put_vocabulary;

    public static final String ARG_VOCABULARY_ID = "VOCABULARY_ID";

    private static final String LOG_TAG = "PUT_VOC_FRAG";

    @BindView(R.id.etTitle) protected EditText mEtTitle;
    @BindView(R.id.etDescription) protected EditText mEtDescription;

    @BindString(R.string.empty_title_error_message) protected String emptyTitleErrorMessage;

    @Nullable
    private Long mVocabularyId = null;

    @Inject
    protected PutVocabularyPresenter mPresenter;

    public PutVocabularyFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LexiconCoachApp.get(getContext()).getAppComponent().injectInto(this);
        mPresenter.bindView(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mVocabularyId != null) {
            outState.putLong(ARG_VOCABULARY_ID, mVocabularyId);
        }
    }

    @Nullable
    @Override
    public Long getVocabularyId() {
        return mVocabularyId;
    }

    @Override
    public void setVocabularyId(Long vocabularyId) {
        mVocabularyId = vocabularyId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);
        ButterKnife.bind(this, view);
        if (savedInstanceState != null && savedInstanceState.containsKey(ARG_VOCABULARY_ID)){
            mVocabularyId = savedInstanceState.getLong(ARG_VOCABULARY_ID);
        }
        return view;
    }

    @Override
    public void loadVocabularyData() {
        mPresenter.onLoadDataFor(mVocabularyId);
    }

    @Override
    public void onSaveVocabulary() {
        mPresenter.onSaveVocabulary();
    }

    @Override
    public void onVocabularySaved() {
        Activity parentActivity = getActivity();
        Toast.makeText(getContext(), "Словарь успешно создан!", Toast.LENGTH_SHORT);
        if (parentActivity != null){
            parentActivity.finish();
        }
    }

    @Override
    public void setFields(PutVocabularyVM model) {
        mEtTitle.setText(model.getTitle());
        mEtDescription.setText(model.getTitle());
    }

    @Override
    public void showEmptyTitleError() {
        Toast.makeText(getContext(), emptyTitleErrorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showVocabularyExistsError(String vocabularyTitle) {
        Toast.makeText(getContext(), getString(R.string.vocabulary_already_exists_error_format, vocabularyTitle), Toast.LENGTH_SHORT).show();
    }


    @Override
    public String getTitleField() {
        return mEtTitle.getText().toString();
    }

    @Override
    public String getDescriptionField() {
        return mEtDescription.getText().toString();
    }
}
