package org.cloud.note.service.impl;

import org.cloud.note.NoteApplicationTests;
import org.cloud.note.dto.ServiceResult;
import org.cloud.note.service.NoteCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class NoteCategoryServiceImplTest extends NoteApplicationTests {
    @Autowired
    NoteCategoryService noteCategoryServicen;

    @Test
    void getNoteCategoryByUserId() {
    }

    @Test
    void getNoteCategoryByNameAndUserId() {
    }

    @Test
    void getNoteCategoryById() {
    }

    @Test
    void createNoteCategory() {
    }

    @Test
    void removeNoteCategory() {
        ServiceResult result = noteCategoryServicen.removeNoteCategory("默认", 1);
        System.out.println(result);
    }
}