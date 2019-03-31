package pers.ownsky.trafficalert.publicutils.json;

public class AuthExpiredException extends AuthFailException {
    public AuthExpiredException(String phone) {
        super("Authentication for "+phone+" has expired.");
    }
}
