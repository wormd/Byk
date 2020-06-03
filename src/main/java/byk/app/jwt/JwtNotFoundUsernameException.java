package byk.app.jwt;

import org.springframework.security.core.AuthenticationException;

public class JwtNotFoundUsernameException extends AuthenticationException {
    /**
     *
     */
    private static final long serialVersionUID = 5939849134749982254L;

    public JwtNotFoundUsernameException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtNotFoundUsernameException(String msg) {
        super(msg);
    }

}
