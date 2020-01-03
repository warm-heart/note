package org.cloud.note.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangqianlong
 * @create 2020-01-03 12:27
 */
@Data
public class Role implements Serializable {


    private static final long serialVersionUID = 5718414537187029022L;
    private Integer roleId;
    private String roleName;

}
