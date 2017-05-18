package com.exprod.lexiconcoach.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exprod.lexiconcoach.R;
import com.exprod.lexiconcoach.ui.fragments.VocabularyListFragment;
import com.exprod.lexiconcoach.ui.fragments.VocabularyListFragment.OnVocabularyItemInteractionListener;
import com.exprod.lexiconcoach.viewmodels.VocabularyItemVM;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VocabularyRecyclerViewAdapter extends RecyclerView.Adapter<VocabularyRecyclerViewAdapter.ViewHolder> {

    private List<VocabularyItemVM> mItems;
    private final VocabularyListFragment.OnVocabularyItemInteractionListener mListener;

    public VocabularyRecyclerViewAdapter(List<VocabularyItemVM> items, OnVocabularyItemInteractionListener listener) {
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
                if (mListener != null) {
                    mListener.onVocabularyItemClick(holder.mItem.getId());
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
        @BindView(R.id.tvTitle)
        public TextView mTvTitle;
        @BindView(R.id.tvLastRun)
        public TextView mTvLastRun;
        @BindView(R.id.tvCompletedPercent)
        public TextView mTvCompletedPercent;
        @BindView(R.id.tvTotalWordsCount)
        public TextView mTvTotalWordsCount;
        @BindView(R.id.tvMistakesCount)
        public TextView mTvMistakesCount;
        @BindView(R.id.tvRunCount)
        public TextView mTvRunCount;

        public VocabularyItemVM mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }
    }
}
