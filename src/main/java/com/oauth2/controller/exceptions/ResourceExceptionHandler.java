package com.oauth2.controller.exceptions;

import com.oauth2.entity.Response;
import com.oauth2.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response<User>> userNotFound(){
        return ResponseEntity.badRequest().body(Response.erro("Usuário não encontrado!"));
    }

    @ExceptionHandler(UserNotDeletedException.class)
    public ResponseEntity<Response<User>> userNotDeleted(){
        return ResponseEntity.badRequest().body(Response.erro("Usuário não pode ser deletado!"));
    }

    @ExceptionHandler(NotNullException.class)
    public ResponseEntity<Response<User>> notNul(){
        return ResponseEntity.badRequest().body(Response.erro("Não pode conter campos nulos"));
    }

    @ExceptionHandler(UserNotSavedException.class)
    public ResponseEntity<Response<User>> userNotSaved(){
        return ResponseEntity.badRequest().body(Response.erro("Usuário não pode ser salvo!"));
    }
}
