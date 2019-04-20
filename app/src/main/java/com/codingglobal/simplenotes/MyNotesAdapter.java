package com.codingglobal.simplenotes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyNotesAdapter extends RecyclerView.Adapter<MyNotesAdapter.MyViewHolder> {

    ArrayList<NotesDB> currentNote;
//    public MyNotesAdapter(@NonNull Context context, @NonNull List<NotesDB> notes) {
//        super(context, 0, notes);
//    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View MyNote = convertView;
//
//        if (MyNote == null) {
//            MyNote = LayoutInflater.from(getContext()).inflate(R.layout.note_list_item, parent, false);
//        }
//
//        NotesDB myNoteItem = getItem(position);
//
//        TextView NoteText = MyNote.findViewById(R.id.NoteListItemContent);
//        NoteText.setText(myNoteItem.getNoteContent());
//
//        return MyNote;
//    }

    public MyNotesAdapter(ArrayList<NotesDB> note) {
        currentNote = note;
    }

    @NonNull
    @Override
    public MyNotesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.note_list_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyNotesAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.content.setText(currentNote.get(i).getNoteContent());
    }

    @Override
    public int getItemCount() {
        return currentNote.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        protected TextView content;

        public MyViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.NoteListItemContent);
        }
    }


}
