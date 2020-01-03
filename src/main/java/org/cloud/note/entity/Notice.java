package org.cloud.note.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangqianlong
 * @create 2020-01-03 12:28
 */
@Data
public class Notice implements Serializable {

    private static final long serialVersionUID = 4175805371481304024L;
    private Integer noticeId;
    private String noticeTitle;
    private String noticeContext;
}
