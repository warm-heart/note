package org.cloud.note.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cloud.note.VO.NoteShareVO;
import org.cloud.note.dao.NoteShareDao;
import org.cloud.note.dto.NoteAnalysis;
import org.cloud.note.dto.NoteShareDTO;
import org.cloud.note.dto.ServiceResult;
import org.cloud.note.entity.NoteShare;
import org.cloud.note.exception.NoteException;
import org.cloud.note.service.NoteShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public boolean saveNoteShare(NoteShare noteShare) {
        Integer res = noteShareDao.saveNoteShare(noteShare);
        if (res == 1) {
            return true;
        }
        throw new NoteException("分享失败");
    }

    @Override
    @Transactional
    public boolean removeNoteShareByNoteId(Integer noteId) {
        Integer res = noteShareDao.removeNoteShareByNoteId(noteId);
        if (res >= 1) {
            return true;
        }
        throw new NoteException("取消分享失败");
    }


    @Override
    @Transactional
    public ServiceResult<NoteShareDTO> listNoteShareByPage(Integer page, Integer size) {
        Integer total = noteShareDao.countNoteShare();
        if (total == 0) {
            return ServiceResult.error("暂时没有分享的笔记");
        }
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<NoteShareVO> noteShareVOList = noteShareDao.listNoteShareByPage(page, size);
        for (NoteShareVO noteShareVO : noteShareVOList) {
            String str = noteShareVO.getNoteContext();
            str = str.replaceAll("<.+?>", "");
            str = str.replaceAll("<a>\\s*|\t|\r|\n|&nbsp;|</a>", "");
            noteShareVO.setNoteContext(str);
        }

        NoteShareDTO noteShareDTO = new NoteShareDTO();
        noteShareDTO.setNoteShareVOS(noteShareVOList);
        noteShareDTO.setTotal(total);
        return ServiceResult.success(noteShareDTO);
    }


    @Override
    @Transactional
    public ServiceResult<String> love(Integer noteId) {
        Integer res = noteShareDao.incrementLoveCount(noteId);
        if (res == 1) {
            return ServiceResult.success("点赞成功");
        }
        return ServiceResult.error("点赞失败");
    }

    @Override
    public List<NoteAnalysis> noteShareAnalysis() {
        return noteShareDao.noteShareAnalysis();
    }
}
