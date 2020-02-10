package org.cloud.note.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wangqianlong
 * @create 2019-12-02 18:42
 */
@Data
public class NoteCategory implements Serializable {

    private static final long serialVersionUID = 8364361245506656314L;
    private Integer categoryId;
    private Integer userId;
    @NotEmpty(message = "笔记分类名称不能为空")
    private String categoryName;
    @NotEmpty(message = "笔记分类描述不能为空")
    private String categoryDescription;
    private Date updateTime;
}
