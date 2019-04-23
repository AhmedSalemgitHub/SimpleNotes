package com.codingglobal.simplenotes;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {


    FloatingActionButton addButton;

    RecyclerView listNotes;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    private final OrderedRealmCollectionChangeListener<RealmResults<NotesDB>> realmChangeListener =
            ((notesDBS, changeSet) -> adapter.notifyDataSetChanged());
    List<NotesDB> content = new ArrayList<>();
    ///////////////////////////
    private Realm realm;
    private RealmResults<NotesDB> results;
    ////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listNotes = findViewById(R.id.ListNotes);
        addButton = findViewById(R.id.floatingActionButtonAdd);

        layoutManager = new LinearLayoutManager(this);
        listNotes.setLayoutManager(layoutManager);

        adapter = new MyAdapter(this, content);
        listNotes.setAdapter(adapter);

        realm = Realm.getDefaultInstance();
        results = realm.where(NotesDB.class).findAll();
        results.addChangeListener(realmChangeListener);

        content.addAll(results);

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), NoteEditActivity.class);
            intent.putExtra("mode", "add");// لتحديد الفتح في حاله الاضافة
            startActivity(intent);
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        results.removeAllChangeListeners();
        realm.close();
    }
}
