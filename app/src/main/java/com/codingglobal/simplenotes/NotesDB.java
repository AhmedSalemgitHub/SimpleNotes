package com.codingglobal.simplenotes;

import io.realm.RealmObject;

public class NotesDB extends RealmObject {

    private int id;
    private String noteTitle,noteContent,noteOwner;

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    String getNoteTitle() {
        return noteTitle;
    }

    void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    String getNoteContent() {
        return noteContent;
    }

    void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    String getNoteOwner() {
        return noteOwner;
    }

    void setNoteOwner(String noteOwner) {
        this.noteOwner = noteOwner;
    }
}
