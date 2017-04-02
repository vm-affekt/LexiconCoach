package com.exprod.lexiconcoach.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exprod.lexiconcoach.LexiconCoachApp;
import com.exprod.lexiconcoach.R;
import com.exprod.lexiconcoach.ui.activities.PutVocabularyActivity;
import com.exprod.lexiconcoach.ui.presenters.VocabularyListPresenter;
import com.exprod.lexiconcoach.ui.views.VocabularyListView;
import com.exprod.lexiconcoach.viewmodels.VocabularyItemVM;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class VocabularyListFragment extends Fragment implements VocabularyListView {
    public static final int TITLE_RES_ID = R.string.vocabularies_tab;
    public static final int POSITION_IN_SECTION = 0;
    public static final int LAYOUT = R.layout.fragment_vocabularies;

    private OnListFragmentInteractionListener mListener;

    @BindView(R.id.rvVocabularies) protected RecyclerView mRvVocabularies;

    @BindView(R.id.fab) protected FloatingActionButton mFabAdd;

    @Inject
    protected VocabularyListPresenter mPresenter;

    private VocabularyRecyclerViewAdapter adapter;


    public VocabularyListFragment() {

    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static VocabularyListFragment newInstance() {
        VocabularyListFragment fragment = new VocabularyListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LexiconCoachApp.get(getContext()).getAppComponent().injectInto(this);
        mPresenter.bindView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);
        ButterKnife.bind(this, view);

        mRvVocabularies.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new VocabularyRecyclerViewAdapter(new ArrayList<VocabularyItemVM>(), mListener);

        mRvVocabularies.setAdapter(adapter);
        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), PutVocabularyActivity.class));
            }
        });

        requestVocabularyList();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void requestVocabularyList() {
        mPresenter.onVocabularyListRequest();
    }

    @Override
    public void setList(List<VocabularyItemVM> list) {
        adapter.setItems(list);

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        //void onListFragmentInteraction(DummyItem item);
    }
}