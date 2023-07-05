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
    @Column(name="loginUsers")
    private String loginUser;
    @Column(name="passwordUsers")
    private String passwordUser;
    private boolean admin = false;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Note> noteSet = new HashSet<>();

    public User() {}

    public User(String loginUser, String passwordUser){
        this.loginUser = loginUser;
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
        return loginUser;
    }

    public void setLogin(String loginUser) {
        this.loginUser = loginUser;
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

