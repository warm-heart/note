package org.cloud.note.dao;

import org.cloud.note.NoteApplicationTests;

import org.cloud.note.entity.Note;
import org.cloud.note.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


class NoteDaoTest extends NoteApplicationTests {
    @Autowired
    NoteDao noteDao;

    @Test
    void findByNoteTitle() {
    }

    @Test
    void findByNoteId() {
        System.out.println(noteDao.getNoteByNoteId(12));
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
        System.out.println(noteDao.countNote());
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

    @Test
    void findByTime() {

        Date startTime = DateUtils.yesterDay(new Date());
        Date endTime = new Date();
        System.out.println(endTime);
        System.out.println(LocalDateTime.now());
        List<Note> noteList = noteDao.findByTime(startTime, endTime);
        System.out.println(noteList.size());
        System.out.println(noteList);
    }

    @Test
    void findByKeyWord() {

        List<Note> noteList = noteDao.listNoteByNoteTitleAndUserId("三", 1);
        System.out.println(noteList.size());
        System.out.println(noteList);

    }

}