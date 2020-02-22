package org.cloud.note.dao;

import org.cloud.note.NoteApplicationTests;

import org.cloud.note.entity.Notice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


class NoticeDaoTest extends NoteApplicationTests {
    @Autowired
    NoticeDao noticeDao;

    @Test
    //@Transactional
    void test() throws InterruptedException {

        Notice notice = new Notice();
        notice.setNoticeId(4);
        notice.setNoticeTitle("4");
        notice.setNoticeContext("4");
        noticeDao.updateNotice(notice);
        Thread.sleep(10000);
        System.out.println("end");
    }

}