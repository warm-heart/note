package org.cloud.note.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wangqianlong
 * @create 2020-01-03 12:28
 */
@Data
public class Notice implements Serializable {

    private static final long serialVersionUID = 4175805371481304024L;
    private Integer noticeId;
    @NotEmpty(message = "公告标题不能为空")
    private String noticeTitle;
    @NotEmpty(message = "公告内容不能为空")
    private String noticeContext;

    Integer noticeType;

    private Date updateTime;
}
