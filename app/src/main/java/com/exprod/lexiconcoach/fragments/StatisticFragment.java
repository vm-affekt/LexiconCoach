package com.exprod.lexiconcoach.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exprod.lexiconcoach.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatisticFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticFragment extends Fragment {
    public static final int TITLE_RES_ID = R.string.statistic_tab;
    public static final int POSITION_IN_SECTION = 1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistic, container, false);
    }

}
