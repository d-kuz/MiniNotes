package com.github.dkuz.mininotes.model;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PS_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="passwordUser")
    private String passwordUser;
    private boolean admin = false;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Note> noteSet = new HashSet<>();

    public User() {}

    public User(String name, String passwordUser){
        this.name = name;
        this.passwordUser = passwordUser;
    }


    public long getId() {
        return id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getLogin() {
        return name;
    }

    public void setLogin(String loginUser) {
        this.name = loginUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public Set<Note> getNoteSet() {
        return noteSet;
    }

    public void setNoteSet(Set<Note> noteSet) {
        this.noteSet = noteSet;
    }

    public void addNote(Note note) {
        noteSet.add(note);
    }
    public void deleteNote(Note note) {
        noteSet.remove(note);
    }
}

