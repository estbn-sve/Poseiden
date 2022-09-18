package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUser(){
        return repository.findAll();
    }

    public User getUser(final Integer id){
        return repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with getUser"+id));
    }

    public User addUser(User domain){
        Integer id = domain.getId();
        if(id == null || !repository.existsById(id)){
            return repository.save(domain);
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with addUser"+id));
        }
    }

    public User putUser(User currentDomain, final Integer id){
        if(repository.existsById(id)){
            repository.save(currentDomain);
            return currentDomain;
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with putUser "+id));
        }
    }

    public User deleteUser(final Integer id){
        User domain = repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with deleteUser "+id));
        User copy = User.builder()
                .id(domain.getId())
                .fullname(domain.getFullname())
                .username(domain.getUsername())
                .password(domain.getPassword())
                .role(domain.getPassword())
                .build();
        repository.delete(domain);
        return copy;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User userApp = repository.findByUsername(s).orElseThrow(()->
                new UsernameNotFoundException("Error with loadUserByUsername "+s));
        Set<SimpleGrantedAuthority> listAuthority = Collections.singleton(new SimpleGrantedAuthority(userApp.getRole()));
        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(userApp.getUsername(), userApp.getPassword(),listAuthority);
        return user;
    }
}

