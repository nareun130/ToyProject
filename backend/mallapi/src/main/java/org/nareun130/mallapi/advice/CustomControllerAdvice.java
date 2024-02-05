package org.nareun130.mallapi.advice;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * 커스텀 ControllerAdvice
 * -> 유효하지 않은 파라미터 같은 것들을 다루기 위해
 */
@RestControllerAdvice
public class CustomControllerAdvice {
    
    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<?> notExist(NoSuchElementException e) {

        String msg = e.getMessage();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg",msg));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?>
    handleIllegalArgumentException(MethodArgumentNotValidException e) {

        String msg = e.getMessage();

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Map.of("msg",msg));
    }
}
