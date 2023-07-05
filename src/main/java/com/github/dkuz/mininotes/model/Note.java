package com.github.dkuz.mininotes.model;

import jakarta.persistence.*;
@Entity
@Table(name = "PC_NOTE")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="noteText", length = 2500)
    private String noteText;
    @Column(name="createDateTime")
    private String createDateTime;
    @Column(name="updateDateTime")
    private String updateDateTime;
    @Column(name="isDelete")
    private Boolean isDelete;

    @ManyToOne
    @JoinColumn(name = "idUser")
    public User user;

    public Note() {}

    public Note(String name, String noteText, String createDateTime){
        this.name = name;
        this.noteText = noteText;
        this.createDateTime = createDateTime;
        this.updateDateTime = createDateTime;
        this.isDelete = false;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }
    public void setUpdateDateTime(String updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }
    public String getUpdateDateTime() {
        return updateDateTime;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }
}

