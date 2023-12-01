package com.example.service;

import com.example.entities.Note;
import com.example.repository.NoteDao;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class NoteService {

    @Autowired private NoteDao noteDao;

    public Note add(Note note) {
        return noteDao.add(note);
    }

    public void update(Note note) {
        noteDao.update(note);
    }

    public Note getById(UUID id) {
        return noteDao.getById(id);
    }

    public List<Note> listAll() {
        return noteDao.listAll();
    }

    public void deleteById(UUID id) {
        noteDao.deleteById(id);
    }

    @PostConstruct
    public void init() {
        Note noteOne = new Note();
        noteOne.setTitle("Title note 1");
        noteOne.setContext("Context note 1");
        Note noteTwo = new Note();
        noteTwo.setTitle("Title note 2");
        noteTwo.setContext("Context note 2");
        Note noteThree = new Note();
        noteThree.setTitle("Title note 3");
        noteThree.setContext("Context note 3");
        add(noteOne);
        add(noteTwo);
        add(noteThree);
        log.info("List before changed: " + listAll());

        deleteById(noteOne.getId());

        Note note = getById(noteTwo.getId());
        log.info("Found note: " + note);

        note.setTitle("Update title");
        note.setContext("Update context");
        update(note);

        log.info("List after changed: " + listAll());
    }
}
