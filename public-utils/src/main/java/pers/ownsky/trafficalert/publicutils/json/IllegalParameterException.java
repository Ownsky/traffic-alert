package pers.ownsky.trafficalert.publicutils.json;

import org.springframework.http.HttpStatus;

public class IllegalParameterException extends RestException {
    public IllegalParameterException() {
        super(HttpStatus.BAD_REQUEST, "Illegal argument(s), check your request.");
    }
    public IllegalParameterException(String msg)
    {
        super(HttpStatus.BAD_REQUEST, msg);
    }
}
