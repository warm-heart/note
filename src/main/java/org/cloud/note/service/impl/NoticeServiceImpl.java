package org.cloud.note.service.impl;

import org.cloud.note.dao.NoticeDao;
import org.cloud.note.dto.ApiResponse;
import org.cloud.note.dto.NoticeDTO;
import org.cloud.note.dto.ServiceResult;

import org.cloud.note.entity.Notice;


import org.cloud.note.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wangqianlong
 * @create 2020-02-18 13:28
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeDao noticeDao;

    @Override
    @Transactional
    public ServiceResult<String> createNotice(Notice notice) {
        Integer res = noticeDao.createNotice(notice);
        if (res == 1) {
            return ServiceResult.success("创建成功");
        }
        return ServiceResult.error("创建失败");

    }

    @Override
    @Transactional
    public ServiceResult<String> updateNotice(Notice notice) {
        Integer res = noticeDao.updateNotice(notice);
        if (res == 1) {
            return ServiceResult.success("修改成功");
        }

        return ServiceResult.error("修改失败");
    }

    @Override
    @Transactional
    public ServiceResult<String> removeNoticeById(Integer noticeId) {
        Integer res = noticeDao.removeNoticeById(noticeId);
        if (res == 1) {
            return ServiceResult.success("删除成功");
        }
        return ServiceResult.error("删除失败");
    }

    @Override
    public ServiceResult<NoticeDTO> findAllNoticeByPage(Integer page, Integer size) {
        // 默认从0开始
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        Integer total = noticeDao.total();
        if (total == 0) {
            //throw new NoteException(ResultEnum.YOUR_NOTE_IS_EMPTY);
            return ServiceResult.error("暂时没有公告");
        }
        List<Notice> notices = noticeDao.findAllNoticeByPage(page, size);

        NoticeDTO noticeDTO = new NoticeDTO(notices, total);
        return ServiceResult.success(noticeDTO);
    }

    @Override
    public ServiceResult<Notice> findNoticeById(Integer noticeId) {
        Notice notice = noticeDao.findNoticeById(noticeId);
        if (notice == null) {
            return ServiceResult.error("没有此公告");
        }
        return ServiceResult.success(notice);
    }
}
