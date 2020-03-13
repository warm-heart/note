package org.cloud.note.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cloud.note.dto.NoteAnalysis;
import org.cloud.note.dto.ServiceResult;
import org.cloud.note.dao.NoteTagDao;
import org.cloud.note.entity.NoteTag;
import org.cloud.note.enums.ResultEnum;
import org.cloud.note.exception.NoteException;
import org.cloud.note.service.NoteTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wangqianlong
 * @create 2020-01-03 16:38
 */
@Slf4j
@Service
public class NoteTagServiceImpl implements NoteTagService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    NoteTagDao noteTagDao;

    @Transactional
    @Override
    public ServiceResult<String> saveNoteTag(String noteLabel, Integer noteId) {
        NoteTag noteTag = new NoteTag();
        noteTag.setNoteLabel(noteLabel);
        noteTag.setNoteId(noteId);
        Integer res = noteTagDao.saveNoteTag(noteTag);
        if (res == 1) {
            return ServiceResult.success(ResultEnum.CREATE_NOTE_TAG_SUCCESS.getMessage());
        }
        throw new NoteException(ResultEnum.CREATE_NOTE_TAG_FAIL);
    }

    @Override
    public List<NoteTag> listNoteTagByNoteId(Integer noteId) {
        List<NoteTag> noteTagList = noteTagDao.listNoteTagByNoteId(noteId);

        return noteTagList;
    }

    @Override
    @Transactional
    public Integer removeNoteTagByNoteId(Integer noteId) {
        Integer res = noteTagDao.removeNoteTagByNoteId(noteId);
        //删除失败，日志记录 手动删除
       /* if (res <= 1) {
            log.info("删除NoteId为 {} 的标签失败", noteId);
        }*/
        return res;
    }

    @Override
    public List<NoteAnalysis> noteTagAnalysis() {
        return noteTagDao.noteTagAnalysis();
    }
}
