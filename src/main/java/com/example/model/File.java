package com.example.model;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "PC_FILE")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="name")
    private String name;
    private boolean isDelete;
    private boolean allDelete;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToMany(mappedBy = "file",cascade = CascadeType.ALL)
    private Set<Note> notes = new HashSet<>();

    public File(){

    }

    public File(String name) {
        this.name = name;
        this.isDelete = false;
        allDelete = false;
    }

    public void addNote(Note note){
        notes.add(note);
        note.setAlbum(this);
    }
    public void removeNote(Note note){
        notes.remove(note);
        note.setAlbum(null);
    }

    public boolean isAllDelete() {
        return allDelete;
    }

    public void setAllDelete(boolean allDelete) {
        this.allDelete = allDelete;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }
    public Set<Note> getNotes() {
        return notes;
    }
}