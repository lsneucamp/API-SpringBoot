package co.lsnbox.logistica.exceptions.handlers;


import co.lsnbox.logistica.exceptions.APIBadRequestException;
import co.lsnbox.logistica.exceptions.APIConflictException;
import co.lsnbox.logistica.exceptions.APIConstraintViolationException;
import co.lsnbox.logistica.exceptions.APINotFoundException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.*;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    protected ObjectMapper mapper;
    private final Logger log = LoggerFactory.getLogger(APIExceptionHandler.class);


    public APIExceptionHandler() {
        super();
        mapper = new ObjectMapper();

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), headers, HttpStatus.BAD_REQUEST, request);
    }


    // 400 - General errors
    @ExceptionHandler({APIBadRequestException.class})
    public ResponseEntity<Object> handleBadRequest(final Exception ex, final WebRequest request) throws IOException {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    //403
    @ExceptionHandler({AccessDeniedException.class})
    protected ResponseEntity<Object> handleUnauthorized(final RuntimeException ex, final WebRequest request) throws IOException {
        //logger.error("401 Status Code", ex);
        Map<String, List<String>> msgs = new HashMap<>();
        List<String> messages = new ArrayList<String>();
        messages.add("unauthorized");
        msgs.put("errors", messages);
        String bodyOfResponse = mapper.writeValueAsString(msgs);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    // 404 - Bad Request by Constraint
    @ExceptionHandler({ConstraintViolationException.class, APIConstraintViolationException.class})
    public ResponseEntity<Object> handleForbiddenByConstraintViolation(final ConstraintViolationException ex, final WebRequest request) throws IOException {
        //logger.error("400 Status Code", ex);
        List<String> messages = new ArrayList<String>();
        Set<ConstraintViolation<?>> constraints = ex.getConstraintViolations();
        Map<String, List<String>> msgs = new HashMap<>();

        for (ConstraintViolation<?> constraint : constraints) {
            messages.add(constraint.getMessage());
        }
        msgs.put("errors", messages);
        final String bodyOfResponse = mapper.writeValueAsString(msgs);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    // 404
    @ExceptionHandler(value = {APINotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
        System.out.println(ex.getMessage());
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    // 409
    @ExceptionHandler({APIConflictException.class})
    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    // 500
    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
