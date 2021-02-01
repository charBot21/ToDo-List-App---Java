package com.carlostorres.ibericajava.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carlostorres.ibericajava.R;
import com.carlostorres.ibericajava.data.local.entity.Notes;

import java.util.ArrayList;
import java.util.List;

public class NotsAdapter extends RecyclerView.Adapter<NotsAdapter.NotsHolder> {
    private List<Notes> notes = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public NotsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_notes, parent, false);
        return new NotsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotsHolder holder, int position) {

        Notes current = notes.get(position);
        holder.tvTitle.setText(current.getTitle());
        holder.tvDate.setText(current.getTimeStamp().toString());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    public void setNotes(List<Notes> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }


    public Notes getNoteAt(int position) {
        return notes.get(position);
    }


    class NotsHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvDate;

        public NotsHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvDate = view.findViewById(R.id.tvTimestamp);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();

                    if ( listener != null && pos != RecyclerView.NO_POSITION ) {
                        listener.onItemClick(notes.get(pos));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Notes note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
