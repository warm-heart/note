package org.cloud.note.dao;

import org.cloud.note.NoteApplicationTests;
import org.cloud.note.entity.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class NoteDaoTest extends NoteApplicationTests {
    @Autowired
    NoteDao noteDao;

    @Test
    void findByNoteTitle() {
    }

    @Test
    void findByNoteId() {
    }

    @Test
    void updateNote() {
        Note note = new Note();
        note.setCategoryId(1);
        note.setNoteId(4);
        note.setUserId(1);
        note.setNoteTitle("da");
        note.setNoteDescription("da");
        note.setNoteContext("da");
        note.setNoteStatus(1);
        note.setShareStatus(1);

        System.out.println(noteDao.updateNote(note));
    }

    @Test
    void findAllNoteByPage() {
    }

    @Test
    void getTotal() {
        System.out.println(noteDao.getTotal(1));
    }

    @Test
    void saveNote() {
        Note note = new Note();
        note.setCategoryId(1);
        note.setUserId(1);
        note.setNoteTitle("da");
        note.setNoteDescription("da");
        note.setNoteContext("da");
        System.out.println(noteDao.saveNote(note));
        System.out.println(note.getNoteId());

    }
}