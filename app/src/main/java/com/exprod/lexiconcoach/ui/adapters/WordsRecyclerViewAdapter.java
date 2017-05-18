package com.exprod.lexiconcoach.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exprod.lexiconcoach.R;
import com.exprod.lexiconcoach.ui.fragments.WordsEditFragment;
import com.exprod.lexiconcoach.viewmodels.WordVM;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by VLAD on 10.04.2017.
 */

public class WordsRecyclerViewAdapter extends RecyclerView.Adapter<WordsRecyclerViewAdapter.ViewHolder> {
    private static final String LOG_TAG = "WORDS_RV_ADAPTER";

    private List<WordVM> mItems;

    private WordsEditFragment.OnWordItemInteractionListener mListener;

    public WordsRecyclerViewAdapter(List<WordVM> items, WordsEditFragment.OnWordItemInteractionListener listener) {
        mItems = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mItem = mItems.get(position);
        holder.tvNativeWord.setText(mItems.get(position).getNativeWord());
        holder.tvTranslationWord.setText(mItems.get(position).getTranslationWord());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null){
                    mListener.onWordItemClick(holder.mItem.getTranslationId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(List<WordVM> items){
        mItems = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;

        @BindView(R.id.tvTranslationWord)
        TextView tvTranslationWord;

        @BindView(R.id.tvNativeWord)
        TextView tvNativeWord;

        WordVM mItem;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
