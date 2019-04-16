package com.codingglobal.simplenotes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class NoteEditActivity extends AppCompatActivity {

    Realm realm;
    Button ButtonAddNote;
    Button ButtonEditNote;
    EditText NoteContent;
    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        ButtonAddNote = findViewById(R.id.buttonAddNote);
        ButtonEditNote = findViewById(R.id.buttonEditNote);
        NoteContent = findViewById(R.id.editTextNote);

        mode = getIntent().getStringExtra("mode");

        //اظهار الزر المناسب
        if (mode.equals("add"))
        {
            Toast.makeText(this, "add mode", Toast.LENGTH_SHORT).show();
            ButtonAddNote.setVisibility(View.VISIBLE);
            ButtonEditNote.setVisibility(View.INVISIBLE);
        }
        else if (mode.equals("add")){
            Toast.makeText(this, "edit mode", Toast.LENGTH_SHORT).show();
            ButtonAddNote.setVisibility(View.INVISIBLE);
            ButtonEditNote.setVisibility(View.VISIBLE);
        }
        else{
            Toast.makeText(this, "something wrong", Toast.LENGTH_SHORT).show();
        }



        initialDataBase(this);
        ButtonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotesDB note = new NotesDB();
                note.setId(getNextKey());
                note.setNoteTitle("note");
                note.setNoteOwner("Ahmed");
                note.setNoteContent(NoteContent.getText().toString());

                addData(note);
                MainActivity.adapter.notifyDataSetChanged();
                finish();
            }
        });
    }

    private void addData(NotesDB note) {
        realm.beginTransaction();
        realm.copyToRealm(note);
        realm.commitTransaction();
    }

    private int getNextKey() {
        try {
            return realm.where(NotesDB.class).max("id").intValue() + 1;
        }catch (ArrayIndexOutOfBoundsException | NullPointerException e){
            return 0;
        }
    }

    private void initialDataBase(Context context) {

        Realm.init(context);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);
        realm = Realm.getDefaultInstance();

    }
}
