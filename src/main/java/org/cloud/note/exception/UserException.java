package org.cloud.note.exception;

import org.cloud.note.enums.ResultEnum;

/**
 * @author wangqianlong
 * @create 2019-05-02 10:03
 */
public class UserException extends NoteException {
    private Integer code;

    public UserException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
