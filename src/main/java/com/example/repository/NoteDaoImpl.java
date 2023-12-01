package com.example.repository;

import com.example.entities.Note;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class NoteDaoImpl implements NoteDao {

    private List<Note> notes = new ArrayList<>();

    @Override
    public Note add(Note note) {
        note.setId(UUID.randomUUID());
        this.notes.add(note);
        log.info("Note added");
        return note;
    }

    @Override
    public void update(Note note) {
        notes.set(notes.indexOf(note), note);
        log.info("Note updated");
    }

    @Override
    public Note getById(UUID id) {
        for (Note note : notes) {
            if (note.getId().equals(id)) {
                log.info("Note found.");
                return note;
            }
        }

        log.error("Note doesn't found.");
        return null;
    }

    @Override
    public List<Note> listAll() {
        return this.notes;
    }

    @Override
    public void deleteById(UUID id) {
        for (Note note : notes) {
            if (note.getId().equals(id)) {
                notes.remove(note);
                log.info("Note deleted");
                break;
            }
        }
    }
}
