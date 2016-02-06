package co.lsnbox.logistica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(value = HttpStatus.CONFLICT)
public final class APIConflictException extends RuntimeException implements Supplier<RuntimeException> {

    public APIConflictException()
    {
        super();
    }

    public APIConflictException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    public APIConflictException(final String message)
    {
        super(message);
    }

    public APIConflictException(final Throwable cause)
    {
        super(cause);
    }
    @Override
    public RuntimeException get() {
        return this;
    }
}
