package com.exprod.lexiconcoach.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exprod.lexiconcoach.LexiconCoachApp;
import com.exprod.lexiconcoach.R;
import com.exprod.lexiconcoach.ui.presenters.PutWordPresenter;
import com.exprod.lexiconcoach.ui.views.PutWordView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by VLAD on 27.04.2017.
 */

public class PutWordDialog extends DialogFragment implements PutWordView {
    public static final String TAG = "PUT_WORD_DLG";

    public static final String ARG_VOCABULARY_ID = "VOCABULARY_ID";
    public static final String ARG_TRANSLATION_ID = "TRANSLATION_ID";
    public static final String ARG_IS_EDIT_MODE_ID = "IS_EDIT_MODE_ID";

    private static final String LOG_TAG = TAG;

    private Long mVocabularyId;

    @Nullable
    private Long mTranslationId;

    @BindView(R.id.etForeignWord)
    protected EditText mEtForeignWord;

    @BindView(R.id.etNativeWord)
    protected EditText mEtNativeWord;

    @Inject
    protected PutWordPresenter mPresenter;

    private PutWordDialogInteractionListener mListener;

    private final View.OnClickListener mOnSaveButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mPresenter.onSaveWord();
        }
    };

    private void readStateFromBundle(Bundle args){
        mVocabularyId = args.getLong(ARG_VOCABULARY_ID);
        if (args.getBoolean(ARG_IS_EDIT_MODE_ID)){
            mTranslationId = args.getLong(ARG_TRANSLATION_ID);
        }else{
            mTranslationId = null;
        }
    }


    public static PutWordDialog newInstance(Long vocabularyId, @Nullable Long translationId){
        PutWordDialog dialog = new PutWordDialog();
        Bundle args = new Bundle();
        writeToBundle(args, vocabularyId, translationId);
        dialog.setArguments(args);
        return dialog;
    }

    @NonNull
    private static void writeToBundle(Bundle args, Long vocabularyId, @Nullable Long translationId) {
        args.putLong(ARG_VOCABULARY_ID, vocabularyId);
        if (translationId != null) {
            args.putLong(ARG_TRANSLATION_ID, translationId);
            args.putBoolean(ARG_IS_EDIT_MODE_ID, true);
        }else{
            args.putBoolean(ARG_IS_EDIT_MODE_ID, false);
        }
    }

    public void setInteractionListener(PutWordDialogInteractionListener listener) {
        mListener = listener;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        writeToBundle(outState, mVocabularyId, mTranslationId);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LexiconCoachApp.get(getContext()).getAppComponent().injectInto(this);
        mPresenter.bindView(this);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mListener != null){
            mListener.onDismiss();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            readStateFromBundle(savedInstanceState);
        }else{
            readStateFromBundle(getArguments());
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_put_word, null);
        ButterKnife.bind(this, dialogView);
        String title = isEditMode() ? getString(R.string.edit_translation_dialog_title)
                                    : getString(R.string.add_translation_dialog_title);
        builder.setTitle(title)
                .setView(dialogView)
                .setPositiveButton(getString(R.string.save_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton(getString(R.string.cancel_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        final AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(mOnSaveButtonClickListener);
            }
        });
        mPresenter.onCreate(savedInstanceState);
        return dialog;

    }

    @Override
    public String getForeignWord() {
        return mEtForeignWord.getText().toString();
    }

    @Override
    public String getNativeWord() {
        return mEtNativeWord.getText().toString();
    }

    @Override
    public void setForeignWord(String foreignWord) {
        mEtForeignWord.setText(foreignWord);
    }

    @Override
    public void setNativeWord(String nativeWord) {
        mEtNativeWord.setText(nativeWord);
    }

    @Override
    public Long getVocabularyId() {
        return mVocabularyId;
    }

    @Override
    public Boolean isEditMode() {
        return mTranslationId != null;
    }

    @Override
    public Long getTranslationId() {
        return mTranslationId;
    }

    @Override
    public void showEmptyFieldsError() {
        Toast.makeText(getActivity(), R.string.empty_word_fields_error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWordSaved() {
        dismiss();
    }

    public interface PutWordDialogInteractionListener{
        void onDismiss();
    }
}
