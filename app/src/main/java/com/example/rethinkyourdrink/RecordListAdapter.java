package com.example.rethinkyourdrink;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class RecordListAdapter extends ListAdapter<RecordEntity, RecordListAdapter.RecordListHolder> {
    public RecordListAdapter(@NonNull DiffUtil.ItemCallback<RecordEntity> diffCallback) {
        super(diffCallback);
    }

    @Override
    public RecordListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RecordListHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(RecordListHolder holder, int position) {
        RecordEntity current = getItem(position);
        holder.bind(current.getDay(), current.getType(), current.getAmount(), current.getOthers());
    }

    static class NoteDiff extends DiffUtil.ItemCallback<RecordEntity> {

        @Override
        public boolean areItemsTheSame(@NonNull RecordEntity oldItem, @NonNull RecordEntity newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull RecordEntity oldItem, @NonNull RecordEntity newItem) {
            return oldItem.getType().equals(newItem.getType());
        }
    }
    static class RecordListHolder extends RecyclerView.ViewHolder {
        private TextView typeTextView;
        private TextView dayTextView;
        private TextView TVamount;
        private TextView TVothers;
        View mView;

        public RecordListHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            typeTextView = mView.findViewById(R.id.typeTextView);
            dayTextView = mView.findViewById(R.id.dayTextView);
            TVamount = mView.findViewById(R.id.TVamount);
            TVothers = mView.findViewById(R.id.TVothers);
        }

        static RecordListHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.record_history, parent, false);
            return new RecordListHolder(view);
        }

        public void bind(String day, String type, int amount, String others) {
            dayTextView.setText(day);
            typeTextView.setText(type);
            TVamount.setText(String.valueOf(amount));
            TVothers.setText(others);
        }
    }

}
