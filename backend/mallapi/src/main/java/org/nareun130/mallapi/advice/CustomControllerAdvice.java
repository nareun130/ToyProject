package org.nareun130.mallapi.advice;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.log4j.Log4j2;

/*
 * 커스텀 ControllerAdvice
 * -> 유효하지 않은 파라미터 같은 것들을 다루기 위해
 */
@Log4j2
@RestControllerAdvice
public class CustomControllerAdvice {
    
    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<?> notExist(NoSuchElementException e) {

        String msg = e.getMessage();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg",msg));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?>
    handleIllegalArgumentException(MethodArgumentNotValidException e, BindingResult bResult) {

        String msg = e.getMessage();
        if(bResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError)objectError;

                String message = objectError.getDefaultMessage();
                
                log.info("field : " + field.getField());
                log.info(message);

                sb.append("field : " + field.getField());
                sb.append("message : "+ message);
                
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg",sb.toString()));
        }
        

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Map.of("msg",msg));
    }
    
}
