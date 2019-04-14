package com.codingglobal.simplenotes;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    FloatingActionButton addButton;
    ListView listNotes;
    static ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listNotes = findViewById(R.id.ListNotes);
        addButton = findViewById(R.id.floatingActionButtonAdd);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NoteEditActivity.class);
                startActivity(intent);
            }
        });

        //initialize the database
        initialDataBase(this);

        RealmResults<NotesDB> results = realm.where(NotesDB.class).findAll();
        ArrayList<String> content = new ArrayList<>();
        for ( int i = 0 ; i < results.size(); i++)
        {
            content.add(results.get(i).getNoteContent());
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, content);
        listNotes.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        Log.i("Test","OnResume");
        super.onResume();
        adapter.notifyDataSetChanged();
    }


    private void initialDataBase(Context context) {

        Realm.init(context);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);
        realm = Realm.getDefaultInstance();

    }
}
