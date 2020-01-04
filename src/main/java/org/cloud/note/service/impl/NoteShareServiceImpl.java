package org.cloud.note.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cloud.note.dao.NoteShareDao;
import org.cloud.note.entity.NoteShare;
import org.cloud.note.exception.NoteException;
import org.cloud.note.service.NoteShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangqianlong
 * @create 2020-01-04 13:47
 */
@Service
@Slf4j
public class NoteShareServiceImpl implements NoteShareService {

    @Autowired
    NoteShareDao noteShareDao;

    @Override
    @Transactional
    public boolean saveShare(NoteShare noteShare) {
        Integer res = noteShareDao.saveShare(noteShare);
        if (res == 1) {
            return true;
        }
        throw new NoteException("分享失败");
    }

    @Override
    @Transactional
    public boolean removeShareByNoteId(Integer noteId) {
        Integer res = noteShareDao.removeShareByNoteId(noteId);
        return true;
     /*   if (res==1){
            return true;
        }
        throw new NoteException("取消分享失败");*/
    }

    @Override
    public boolean incrementLoveCount(Integer noteId) {
        Integer res = noteShareDao.incrementLoveCount(noteId);
        if (res == 1) {
            return true;
        }
        throw new NoteException("点赞失败");
    }
}
