package org.cloud.note.dto;

import lombok.Data;
import org.cloud.note.entity.Note;
import org.cloud.note.entity.User;

import java.util.List;

/**
 * @author wangqianlong
 * @create 2020-01-03 12:54
 */
@Data
public class UserDTO {

    private List<User> users;
    private Integer Total;

    public UserDTO() {

    }

    public UserDTO(List<User> users, Integer total) {
        this.users = users;
        Total = total;
    }
}
