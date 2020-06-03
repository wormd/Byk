package byk.app;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomRuntimeException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 8058583992566872810L;

    public CustomRuntimeException(String message) {

        super(message);

    }

}