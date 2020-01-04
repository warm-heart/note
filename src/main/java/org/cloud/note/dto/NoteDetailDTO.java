package org.cloud.note.dto;

import lombok.Data;
import org.cloud.note.entity.Note;
import org.cloud.note.entity.NoteCategory;


import java.util.List;


/**
 * @author wangqianlong
 * @create 2020-01-03 12:57
 */
@Data
public class NoteDetailDTO {

    private Note note;
    private NoteCategory noteCategory;
    private String userName;
    private List<String> noteTagList;
}
