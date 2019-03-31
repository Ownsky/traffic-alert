package pers.ownsky.trafficalert.publicutils.json;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RestException extends RuntimeException {
    HttpStatus status;

    public RestException() {}

    public RestException(int status, String message) {
        super(message);
        this.status = HttpStatus.valueOf(status);
    }

    public RestException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public RestException(HttpStatus status) {
        super(status.getReasonPhrase());
        this.status = status;
    }

    public RestException(int status) {
        this(HttpStatus.valueOf(status));
    }

    public RestException(String msg) {
        super(msg);
        this.status = HttpStatus.I_AM_A_TEAPOT;
    }

    public RestException(Exception e) {
        super(e.getMessage());
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
