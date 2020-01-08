package org.cloud.note.dao;

import org.cloud.note.NoteApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class NoteShareDaoTest extends NoteApplicationTests {
    @Autowired
    NoteShareDao noteShareDao;

    @Test
    void saveShare() {
    }

    @Test
    void removeShareByNoteId() {
    }

    @Test
    void incrementLoveCount() {
    }

    @Test
    void findNoteShareByPage() {
        System.out.println(noteShareDao.findNoteShareByPage(0, 6));
    }

    @Test
    void getTotal() {
    }
}