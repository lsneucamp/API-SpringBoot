package co.lsnbox.logistica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

/**
 * Thrown when user is forbidden to execute specified operation or access specified data.
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class APIForbiddenException extends RuntimeException implements Supplier<RuntimeException> {

    public APIForbiddenException()
    {
        super();
    }

    public APIForbiddenException(final String message)
    {
        super(message);
    }

    public APIForbiddenException(final Throwable cause)
    {
        super(cause);
    }

    public APIForbiddenException(String message, Throwable cause)
    {
        super(message, cause);
    }
    @Override
    public RuntimeException get() {
        return this;
    }
}
