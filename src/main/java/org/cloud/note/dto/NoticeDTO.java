package org.cloud.note.dto;

import lombok.Data;

import org.cloud.note.entity.Notice;

import java.util.List;

/**
 * @author wangqianlong
 * @create 2020-02-18 13:26
 */
@Data
public class NoticeDTO {

    private List<Notice> notices;
    private Integer Total;

    public NoticeDTO() {

    }

    public NoticeDTO(List<Notice> notices, Integer total) {
        this.notices = notices;
        Total = total;
    }
}
