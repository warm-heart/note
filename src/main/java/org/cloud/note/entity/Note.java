package org.cloud.note.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author wangqianlong
 * @create 2019-12-02 18:41
 */
@Data
public class Note implements Serializable {

    private static final long serialVersionUID = 4703746884134809521L;

    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private String noteContext;
    private Integer noteStatus;
    private Integer shareStatus;
    private Integer categoryId;
    private Integer userId;
    private Date updateTime;

}
