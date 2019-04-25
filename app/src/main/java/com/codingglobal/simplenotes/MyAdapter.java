package com.codingglobal.simplenotes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.NoteHolder> {

    private Context context;
    private List<NotesDB> list;
    public Realm realm;

    MyAdapter(Context context, List<NotesDB> list) {
        this.context = context;
        this.list = list;
        realm = Realm.getDefaultInstance();
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_list_item, viewGroup, false);

        return new NoteHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int i) {
        final NotesDB note = list.get(i);
        noteHolder.tv.setText(note.getNoteContent());
        noteHolder.mainView.setOnClickListener(v -> {
            Intent intent = new Intent(context, NoteEditActivity.class);
            intent.putExtra("selectedId", note.getId());
            intent.putExtra("mode", "edit");
            context.startActivity(intent);
        });

        noteHolder.mainView.setOnLongClickListener(v -> {

            realm.executeTransaction(realm -> {
                RealmResults<NotesDB> resultsForDelete =
                        realm.where(NotesDB.class).equalTo("id", note.getId()).findAll();
                resultsForDelete.deleteAllFromRealm();
                list.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, list.size());
            });
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ConstraintLayout mainView;

        NoteHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.NoteListItemContent);
            mainView = itemView.findViewById(R.id.mainView);
        }
    }
}
