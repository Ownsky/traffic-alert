package pers.ownsky.trafficalert.publicutils.json;

import org.springframework.http.HttpStatus;

public class LoginFailException extends UserNotFoundException {
    public LoginFailException(String phone) {
        super(phone, null);
//        status = HttpStatus.UNAUTHORIZED;
    }
}
