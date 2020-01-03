package org.cloud.note.exception;

import org.cloud.note.enums.ResultEnum;

/**
 * @author wangqianlong
 * @create 2019-05-02 10:03
 */
public class NoteException extends NoteBasicException {
    private Integer code;

    public NoteException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public NoteException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoteException(String message) {
        super(message);
    }

    public NoteException(Integer code, String message) {
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
