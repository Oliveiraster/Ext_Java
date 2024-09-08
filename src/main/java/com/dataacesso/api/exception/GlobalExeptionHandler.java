package com.dataacesso.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExeptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> tratamentoExecaoValidacao(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String atributo = ((FieldError) error).getField();
            String mensagem = error.getDefaultMessage();
            errors.put(atributo, mensagem);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String , String > userNotFoundException(UserNotFoundException ex){
        Map<String , String> errosMap = new HashMap<String, String>();
        errosMap.put("message", ex.getMessage());
        return errosMap;
    }



    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RegisterNotFoundException.class)
    public Map<String , String > registerNotFoundException(RegisterNotFoundException ex){
        Map<String , String> errosMap = new HashMap<String, String>();
        errosMap.put("message", ex.getMessage());
        return errosMap;
    }

}
