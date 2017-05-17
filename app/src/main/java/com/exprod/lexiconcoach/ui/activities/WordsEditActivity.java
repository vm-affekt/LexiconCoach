package com.exprod.lexiconcoach.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.exprod.lexiconcoach.R;
import com.exprod.lexiconcoach.ui.fragments.WordsEditFragment;

public class WordsEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Long vocabularyId = getIntent().getLongExtra(WordsEditFragment.ARG_VOCABULARY_ID, -1);

        getFragment().setVocabularyId(vocabularyId);
        getFragment().requestWordList();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragment().onAddWordClicked();
            }
        });
    }

    private WordsEditFragment getFragment(){
        return (WordsEditFragment)getSupportFragmentManager().findFragmentById(R.id.fragWordsEdit);
    }

}
