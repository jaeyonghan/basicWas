package was.exception;

public class BasicWasException extends Exception {
    public String message;
    public Integer code;

    public BasicWasException(Integer code) {
        this.code = code;
    }

    public BasicWasException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}
