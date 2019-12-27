package org.cloud.note.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangqianlong
 * @create 2019-12-27 15:00
 */
@Data
public class NoteShare implements Serializable {

    private static final long serialVersionUID = 4129132450601811894L;
    private Integer shareId;
    private Integer userId;
    private Integer noteId;
    private Integer loveCount;
    private Date updateTime;
}
