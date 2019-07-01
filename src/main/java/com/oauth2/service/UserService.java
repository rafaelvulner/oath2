package com.oauth2.service;

import com.oauth2.controller.exceptions.NotNullException;
import com.oauth2.controller.exceptions.UserNotDeletedException;
import com.oauth2.controller.exceptions.UserNotFoundException;
import com.oauth2.controller.exceptions.UserNotSavedException;
import com.oauth2.entity.User;
import com.oauth2.repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> findAll(Pageable page){
        return this.userRepository.findAll(page);
    }

    public List<User> findAll(){
        return this.userRepository.findAll();
    }

    public User save(User user){

        User saved = null;

        try {
            saved = this.userRepository.save(user);
        }catch (DataIntegrityViolationException | ConstraintViolationException e){
            throw new UserNotSavedException("Usuário não pode ser salvo: " + e.getMessage());
        }
        return saved;
    }

    public void update(User user){

        Optional<User> userFind = this.userRepository.findById(user.getId());

        if(!userFind.isPresent()){
            throw new UserNotFoundException();
        }

        try {
            this.userRepository.save(user);
        }catch (DataIntegrityViolationException | ConstraintViolationException e){
            throw new NotNullException();
        }

    }

    public void delete(Integer id){

        if(id != null){
            this.userRepository.deleteById(id);
        }else{
            throw new UserNotDeletedException("Usuário invalido");
        }
    }

    public User findById(Integer id){
        Optional<User> user = this.userRepository.findById(id);

        if (!user.isPresent()){
            throw new UserNotFoundException("Usuario não encontrado");
        }

        return user.get();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = this.userRepository.findByUserName(s);
        return user;
    }
}
