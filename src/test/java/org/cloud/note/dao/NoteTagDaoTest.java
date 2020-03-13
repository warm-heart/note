package org.cloud.note.dao;

import org.cloud.note.NoteApplicationTests;


import org.cloud.note.entity.NoteTag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class NoteTagDaoTest extends NoteApplicationTests {
    @Autowired
    NoteTagDao noteTagDao;

    @Test
    void findByNoteId() {
    }

    @Test
    void findByNoteTagId() {
    }

    @Test
    void updateNoteTag() {
    }

    @Test
    void findAllNoteTagByPage() {
    }

    @Test
    void saveNoteTag() {
        NoteTag noteTag = new NoteTag();
        noteTag.setNoteLabel("标签一");
        noteTag.setNoteId(1);
        System.out.println(noteTagDao.saveNoteTag(noteTag));
    }

    @Test
    void getTotal() {
        System.out.println(noteTagDao.noteTagAnalysis());
    }
}