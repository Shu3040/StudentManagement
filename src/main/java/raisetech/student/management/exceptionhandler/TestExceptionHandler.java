package raisetech.student.management.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import raisetech.student.management.exception.TestException;

public class TestExceptionHandler extends Exception{
    @ExceptionHandler(TestExceptionHandler.class)
    public ResponseEntity<String> handleTestException(TestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
