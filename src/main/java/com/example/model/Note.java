package com.example.model;
import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "PC_NOTE")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="noteBody")
    private String noteBody;
    private boolean delete;
    private String updateDateTime;
    private String createDateTime;


    @ManyToOne
    @JoinColumn(name = "File_id")
    private File file;

    public void setFile(File file) {
        this.file = file;
    }

    public Note(String name, String noteBody) {
        this.name = name;
        this.noteBody = noteBody;
        delete = false;
        this.createDateTime = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(new Date());
        this.updateDateTime = createDateTime;

    }

    public Note() {
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setAlbum(File file) {
        this.file = file;
    }
    public File getFile() {
        return file;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(String updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

}

