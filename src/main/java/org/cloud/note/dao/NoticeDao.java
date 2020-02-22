package org.cloud.note.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cloud.note.entity.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangqianlong
 * @create 2020-02-18 13:05
 */
@Mapper
@Repository
public interface NoticeDao {


    Integer createNotice(Notice notice);

    Integer updateNotice(Notice notice);

    Integer removeNoticeById(Integer noticeId);

    List<Notice> findAllNoticeByPage(@Param(value = "page") Integer page,
                                     @Param(value = "size") Integer size);

    Integer total();

    Notice findNoticeById(Integer noticeId);


}
