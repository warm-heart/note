package org.cloud.note.VO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;


/**
 * @author wangqianlong
 * @create 2020-01-03 12:57
 */
@Data
public class NoteVO {
    private Integer noteId;
    @NotEmpty(message = "笔记标题不能为空")
    private String noteTitle;
    @NotEmpty(message = "笔记描述不能为空")
    private String noteDescription;
    @NotEmpty(message = "笔记内容不能为空")
    private String noteContext;

    private List<String> noteTagList;
    @NotEmpty(message = "笔记所在分类不能为空")
    private String categoryName;
}
