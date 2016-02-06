package co.lsnbox.logistica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public final class APIBadRequestException extends RuntimeException implements Supplier<RuntimeException> {

    public APIBadRequestException()
    {
        super();
    }

    public APIBadRequestException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    public APIBadRequestException(final String message)
    {
        super(message);
    }

    public APIBadRequestException(final Throwable cause)
    {
        super(cause);
    }
    @Override
    public RuntimeException get() {
        return this;
    }
}
