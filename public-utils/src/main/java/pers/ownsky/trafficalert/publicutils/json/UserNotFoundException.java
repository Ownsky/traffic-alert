package pers.ownsky.trafficalert.publicutils.json;

import org.springframework.http.HttpStatus;
import pers.ownsky.trafficalert.publicutils.model.User;

public class UserNotFoundException extends RestException {
    public UserNotFoundException(String phone) {
        super(HttpStatus.NOT_FOUND, "User with phone"+phone+" not found.");
    }

    public UserNotFoundException(String phone, String password) {
        super(HttpStatus.FORBIDDEN, "Login for "+phone+" error, wrong phone or password.");
    }
}
