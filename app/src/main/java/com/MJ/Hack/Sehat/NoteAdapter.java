package com.MJ.Hack.Sehat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
public class NoteAdapter extends FirestoreRecyclerAdapter<Note, NoteAdapter.NoteHolder> {
    public NoteAdapter(@NonNull FirestoreRecyclerOptions<Note> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull NoteHolder holder, int position, @NonNull Note model) {
        Log.i("Note adapter-------",model.getYoga());
        holder.textViewTitle.setText(model.getYoga());
        holder.textViewDescription.setText(model.getDesc());
        holder.textViewPriority.setText(String.valueOf(model.getLink()));
    }
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,
                parent, false);
        return new NoteHolder(v);
    }
    class NoteHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        ImageView yogaimage;
        TextView textViewDescription;
        TextView textViewPriority;
        public NoteHolder(View itemView) {
            super(itemView);


        }
    }
}