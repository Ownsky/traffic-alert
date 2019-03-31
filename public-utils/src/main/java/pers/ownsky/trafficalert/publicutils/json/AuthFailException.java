package pers.ownsky.trafficalert.publicutils.json;

import org.springframework.http.HttpStatus;

public class AuthFailException extends RestException {
    public AuthFailException(String msg) {
        super(msg);
        status = HttpStatus.UNAUTHORIZED;
    }
}
