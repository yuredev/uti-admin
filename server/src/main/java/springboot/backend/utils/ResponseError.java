package springboot.backend.utils;

public class ResponseError {
    private final String message;
    private String description;

    public ResponseError(String message, String description) {
        this.message = message;
        this.description = description;
    }

    public ResponseError(String message) {
        this.message = message;
    }
}
