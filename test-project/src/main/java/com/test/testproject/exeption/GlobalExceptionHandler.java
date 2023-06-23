package com.test.testproject.exeption;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex)
    {
        int status = HttpStatus.BAD_REQUEST.value();
        return ResponseEntity
                .status(status)
                .body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException ex)
    {
        return ResponseEntity
                .internalServerError()
                .body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex)
    {
        int status = HttpStatus.NOT_FOUND.value();
        return ResponseEntity
                .status(status)
                .body(ex.getMessage() + String.format("(id=%s)", ex.getErrorObjId()));
    }
}
