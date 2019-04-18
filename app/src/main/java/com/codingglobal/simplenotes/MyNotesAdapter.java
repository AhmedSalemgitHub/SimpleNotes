package com.codingglobal.simplenotes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MyNotesAdapter extends ArrayAdapter<NotesDB> {

    public MyNotesAdapter(@NonNull Context context, @NonNull List<NotesDB> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View MyNote = convertView;

        if (MyNote == null) {
            MyNote = LayoutInflater.from(getContext()).inflate(R.layout.note_list_item, parent, false);
        }

        NotesDB myNoteItem = getItem(position);

        TextView NoteText = MyNote.findViewById(R.id.NoteListItemContent);
        NoteText.setText(myNoteItem.getNoteContent());

        return MyNote;
    }
}
