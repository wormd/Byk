package byk.app.jwt;

import org.springframework.security.core.AuthenticationException;

public class JwtNotFoundUsernameException extends AuthenticationException {
    public JwtNotFoundUsernameException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtNotFoundUsernameException(String msg) {
        super(msg);
    }

}
