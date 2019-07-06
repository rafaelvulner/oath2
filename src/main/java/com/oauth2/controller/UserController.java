package com.oauth2.controller;

import com.oauth2.entity.Response;
import com.oauth2.entity.User;
import com.oauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Page<User>> findAll(Pageable page){
        return ResponseEntity.ok().body(userService.findAll(page));
    }

    @GetMapping("/roleUser")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<String> roleUser(){
        return ResponseEntity.ok().body("Usuario pode ver.");
    }

    @GetMapping("/roleAdmin")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<String> roleAdmin(){
        return ResponseEntity.ok().body("Usuario pode ver.");
    }


    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<User> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Response<User>> save(@RequestBody User user){
        return ResponseEntity.ok().body(Response.create(this.userService.save(user)));
    }

    @PutMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Response> update(@RequestBody User user){
        this.userService.update(user);
        return ResponseEntity.ok().body(Response.message("Atualizado com sucesso!"));
    }

    @DeleteMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Response> delete(@RequestParam("id") Integer id){
        this.userService.delete(id);
        return ResponseEntity.ok().body(Response.message("Excluido com sucesso!"));
    }
}
