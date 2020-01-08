package org.cloud.note.dto;

import lombok.Data;
import org.cloud.note.VO.NoteShareVO;

import java.util.List;

/**
 * @author wangqianlong
 * @create 2020-01-06 16:32
 */
@Data
public class NoteShareDTO {
    List<NoteShareVO> noteShareVOS;
    Integer total;
}
