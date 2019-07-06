package com.oauth2.config;

import com.oauth2.entity.Role;
import com.oauth2.entity.User;
import com.oauth2.repository.RoleRepository;
import com.oauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserService userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){

        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            this.createUsers("Rafael Elias", "rafaell.elias@hotmail.com", "rafaelvulner", passwordEncoder.encode("123456"), "ROLE_ADMIN");
            this.createUsers("Carmo", "carmo@hotmail.com", "carmo", passwordEncoder.encode("123456"), "ROLE_USER");
        }

    }

    public void createUsers(String name, String email, String userName, String password, String role){
        User user = new User(name, email, userName, password, Arrays.asList(new Role(role)));
        userRepository.save(user);

    }

}
