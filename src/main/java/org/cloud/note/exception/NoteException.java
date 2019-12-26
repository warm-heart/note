package org.cloud.note.exception;

/**
 * @author wangqianlong
 * @create 2019-12-25 14:52
 */

public class NoteException extends RuntimeException {
    private Integer code;


    public NoteException(String message) {
        super(message);
    }

    public NoteException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoteException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
