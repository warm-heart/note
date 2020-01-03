package org.cloud.note.dto;

import lombok.Data;
import org.cloud.note.entity.Note;


import java.util.List;

/**
 * @author wangqianlong
 * @create 2020-01-03 12:54
 */
@Data
public class NoteDTO {

    private List<Note> noteList;
    private Integer Total;

    public NoteDTO() {

    }

    public NoteDTO(List<Note> noteList, Integer total) {
        this.noteList = noteList;
        Total = total;
    }
}
