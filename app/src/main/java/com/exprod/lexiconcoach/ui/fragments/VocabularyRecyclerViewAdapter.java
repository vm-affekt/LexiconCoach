package com.exprod.lexiconcoach.ui.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exprod.lexiconcoach.R;
import com.exprod.lexiconcoach.ui.fragments.VocabularyListFragment.OnListFragmentInteractionListener;
import com.exprod.lexiconcoach.viewmodels.VocabularyItemVM;

import java.util.List;

public class VocabularyRecyclerViewAdapter extends RecyclerView.Adapter<VocabularyRecyclerViewAdapter.ViewHolder> {

    private List<VocabularyItemVM> mItems;
    private final OnListFragmentInteractionListener mListener;

    public VocabularyRecyclerViewAdapter(List<VocabularyItemVM> items, OnListFragmentInteractionListener listener) {
        mItems = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vocabulary_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mItems.get(position);
        holder.mTvTitle.setText(mItems.get(position).getTitle());
        String lastRunDateString = mItems.get(position).getLastRunDateString();
        if (lastRunDateString != null){
            holder.mTvLastRun.setText(holder.mView.getContext().getString(R.string.last_run_format, lastRunDateString));
        }else{
            holder.mTvLastRun.setText(R.string.last_run_never);
        }
        holder.mTvCompletedPercent.setText(mItems.get(position).getCompletedPercent() + "%");
        holder.mTvTotalWordsCount.setText(mItems.get(position).getTotalWordsCount().toString());
        holder.mTvMistakesCount.setText(mItems.get(position).getMistakesCount().toString());
        holder.mTvRunCount.setText(mItems.get(position).getRunCount().toString());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    public void setItems(List<VocabularyItemVM> items){
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTvTitle;
        public final TextView mTvLastRun;
        public final TextView mTvCompletedPercent;
        public final TextView mTvTotalWordsCount;
        public final TextView mTvMistakesCount;
        public final TextView mTvRunCount;
        public VocabularyItemVM mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTvTitle = (TextView) view.findViewById(R.id.tvTitle);
            mTvLastRun = (TextView) view.findViewById(R.id.tvLastRun);
            mTvCompletedPercent = (TextView) view.findViewById(R.id.tvCompletedPercent);
            mTvTotalWordsCount = (TextView) view.findViewById(R.id.tvTotalWordsCount);
            mTvMistakesCount = (TextView) view.findViewById(R.id.tvMistakesCount);
            mTvRunCount = (TextView) view.findViewById(R.id.tvRunCount);
        }
    }
}
