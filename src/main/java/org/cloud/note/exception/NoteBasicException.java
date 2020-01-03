package org.cloud.note.exception;

/**
 * @author wangqianlong
 * @create 2019-12-25 14:52
 */

public class NoteBasicException extends RuntimeException {
    private Integer code;


    public NoteBasicException(String message) {
        super(message);
    }

    public NoteBasicException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoteBasicException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
