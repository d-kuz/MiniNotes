package com.github.dkuz.mininotes.repository;


import com.github.dkuz.mininotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface NoteRep extends JpaRepository<Note, Long> {
}