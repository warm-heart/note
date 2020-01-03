package org.cloud.note.dao;

import org.cloud.note.NoteApplicationTests;
import org.cloud.note.entity.NoteCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class NoteCategoryDaoTest extends NoteApplicationTests {


    @Autowired
    NoteCategoryDao noteCategoryDao;

    @Test
    void findByUserId() {
    }

    @Test
    void updateNoteCategory() {
    }

    @Test
    void findByCategoryId() {
    }

    @Test
    void getTotal() {
    }

    @Test
    void saveNoteCategory() {
        NoteCategory noteCategory = new NoteCategory();
        noteCategory.setCategoryName("英语");
        noteCategory.setCategoryDescription("英语");
        noteCategory.setUserId(1);
        Integer res = noteCategoryDao.saveNoteCategory(noteCategory);
        System.out.println(res);

    }
}