package org.cloud.note.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangqianlong
 * @create 2019-12-27 15:00
 */
@Data
public class NoteTag implements Serializable {

    private static final long serialVersionUID = -7766289250423745821L;
    private Integer tagId;
    private Integer noteId;
    private String noteLabel;
    private Date updateTime;
}
