package org.cloud.note.service;

import org.apache.ibatis.annotations.Param;
import org.cloud.note.dto.NoticeDTO;
import org.cloud.note.dto.ServiceResult;
import org.cloud.note.entity.Notice;



/**
 * @author wangqianlong
 * @create 2020-02-18 13:25
 */

public interface NoticeService {


    ServiceResult<String> createNotice(Notice notice);

    ServiceResult<String> updateNotice(Notice notice);

    ServiceResult<String> removeNoticeById(Integer noticeId);

    ServiceResult<NoticeDTO> findAllNoticeByPage(@Param(value = "page") Integer page,
                                                 @Param(value = "size") Integer size);

    ServiceResult<Notice> findNoticeById(Integer noticeId);


}
