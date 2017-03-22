package com.exprod.lexiconcoach.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exprod.lexiconcoach.R;
import com.exprod.lexiconcoach.fragments.dummy.DummyContent;
import com.exprod.lexiconcoach.fragments.dummy.DummyContent.DummyItem;
import com.exprod.lexiconcoach.viewmodels.VocabularyItemVM;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class VocabularyListFragment extends Fragment {
    public static final int TITLE_RES_ID = R.string.vocabularies_tab;
    public static final int POSITION_IN_SECTION = 0;

    private OnListFragmentInteractionListener mListener;

    @BindView(R.id.rvVocabularies) protected RecyclerView mRvVocabularies;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vocabulary, container, false);
        ButterKnife.bind(this, view);

        mRvVocabularies.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRvVocabularies.setAdapter(new VocabularyRecyclerViewAdapter(VocabularyItemVM.getTestList(), mListener));

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
        void onListFragmentInteraction(DummyItem item);
    }
}
