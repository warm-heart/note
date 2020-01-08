package org.cloud.note.VO;

import lombok.Data;

import java.util.Date;

/**
 * @author wangqianlong
 * @create 2020-01-04 15:30
 */
@Data
public class NoteShareVO {
    private Integer shareId;
    private Integer userId;
    private Integer noteId;
    private Integer loveCount;
    private Date updateTime;


    private String noteTitle;
    private String noteDescription;
    private String noteContext;
    private Integer noteStatus;
    private Integer shareStatus;
    private Integer categoryId;




}
