package com.exprod.lexiconcoach.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.exprod.lexiconcoach.R;
import com.exprod.lexiconcoach.ui.fragments.PutVocabularyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import butterknife.Optional;

import static java.security.AccessController.getContext;

public class PutVocabularyActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_vocabulary);
        ButterKnife.bind(this);
        setActionBar();

        if (getIntent().hasExtra(PutVocabularyFragment.ARG_VOCABULARY_ID)){
            getFragment().setVocabularyId(getIntent().getLongExtra(PutVocabularyFragment.ARG_VOCABULARY_ID, -1));
            getFragment().loadVocabularyData();
        }
    }

    private PutVocabularyFragment getFragment(){
        return (PutVocabularyFragment) getSupportFragmentManager().findFragmentById(R.id.fragPutVocabulary);
    }
    private void setActionBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_put_vocabulary, menu);
        return true;
    }

    public void onSaveActionClicked(MenuItem item){
        getFragment().onSaveVocabulary();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
