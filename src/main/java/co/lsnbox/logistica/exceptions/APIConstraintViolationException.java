package co.lsnbox.logistica.exceptions;

import java.util.Set;
import java.util.function.Supplier;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public final class APIConstraintViolationException extends ConstraintViolationException  implements Supplier<RuntimeException> {

    public APIConstraintViolationException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }

    public APIConstraintViolationException(String message, Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(message, constraintViolations);
    }
    @Override
    public RuntimeException get() {
        return this;
    }



}
