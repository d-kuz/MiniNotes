package com.example.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PC_USER")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<File> files = new HashSet<>();

    public User(){}

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

    public void addFile(File file){
        files.add(file);
        file.setUser(this);
    }
    public void removeFile(File file){
        files.remove(file);
        file.setUser(null);
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
    public void setFiles(Set<File> files) {
        this.files = files;
    }
    public Set<File> getFiles() {
        return files;
    }


}
