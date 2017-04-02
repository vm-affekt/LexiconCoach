package com.exprod.lexiconcoach.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exprod.lexiconcoach.LexiconCoachApp;
import com.exprod.lexiconcoach.R;
import com.exprod.lexiconcoach.storage.entities.VocabularyEntity;
import com.exprod.lexiconcoach.storage.meta.VocabulariesMetaTable;
import com.exprod.lexiconcoach.ui.presenters.VocabularyListPresenter;
import com.exprod.lexiconcoach.ui.views.VocabularyListView;
import com.exprod.lexiconcoach.viewmodels.VocabularyItemVM;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatisticFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticFragment extends Fragment {
    public static final int TITLE_RES_ID = R.string.statistic_tab;
    public static final int POSITION_IN_SECTION = 1;
    public static final int LAYOUT = R.layout.fragment_statistic;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.tvFoo)
    protected TextView mTvFoo;

    public StatisticFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static StatisticFragment newInstance() {
        StatisticFragment fragment = new StatisticFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LexiconCoachApp.get(getContext()).getAppComponent().injectInto(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
