package co.lsnbox.logistica.exceptions;

import java.util.function.Supplier;

public class APIUnimplementedException extends RuntimeException implements Supplier<RuntimeException> {

    private String _response = "This method is not implemented yet";

    public APIUnimplementedException() {
    }

    public APIUnimplementedException(String message)
    {
        super(message);
    }

    public APIUnimplementedException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public APIUnimplementedException(Throwable cause)
    {
        super(cause);
    }

    @Override
    public RuntimeException get() {
        return this;
    }
}
