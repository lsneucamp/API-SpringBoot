package co.lsnbox.logistica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public final class APINotFoundException extends RuntimeException implements Supplier<RuntimeException> {

    public APINotFoundException() {
        super();
    }

    public APINotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public APINotFoundException(String message)
    {
        super(message);
    }

    public APINotFoundException(Throwable cause)
    {
        super(cause);
    }

    @Override
    public RuntimeException get() {
        return this;
    }
}
