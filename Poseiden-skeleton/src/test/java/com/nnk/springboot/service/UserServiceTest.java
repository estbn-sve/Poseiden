package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTest {
    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    //all
    @Test
    @WithMockUser(value = "spring")
    public void getAllUser(){
        List<User> Users = new ArrayList<>();
        when(repository.findAll()).thenReturn(Users);
        assertEquals(service.getAllUser(),Users);
    }

    //get
    @Test
    @WithMockUser(value = "spring")
    public void getUser_shouldReturnOk(){
        User domain = new User();
        when(repository.findById(any())).thenReturn(Optional.of(domain));
        assertEquals(service.getUser(1),domain);
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void getUser_shouldThrowNoSuchElement(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.getUser(1);
    }
    //update
    @Test
    @WithMockUser(value = "spring")
    public void putUser_shouldReturnOk(){
        User domain = new User();
        when(repository.findById(any())).thenReturn(Optional.of(domain));
        assertEquals(service.putUser(domain,1),domain);
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void putUser_shouldThrowNoSuchElement(){
        User domain = new User();
        when(repository.existsById(any())).thenReturn(false);
        service.putUser(domain,1);
    }
    //add
    @Test
    @WithMockUser(value = "spring")
    public void addUser_shouldReturnOk(){
        User domain = new User();
        domain.setId(1);
        when(repository.existsById(any())).thenReturn(false);
        when(repository.save(any())).thenReturn(domain);
        assertEquals(service.addUser(domain),domain);
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void addUser_shouldThrowNoSuchElement(){
        User domain = new User();
        domain.setId(1);
        when(repository.existsById(any())).thenReturn(true);
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.addUser(domain);
    }
    //delete
    @Test
    @WithMockUser(value = "spring")
    public void deleteUser_shouldReturnOk(){
        User domain = new User();
        domain.setUsername("test");
        when(repository.findById(any())).thenReturn(Optional.of(domain));
        doNothing().when(repository).delete(any());
        User domainResult = service.deleteUser(1);
        assertEquals(domain.getUsername(), domainResult.getUsername());
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void deleteUser_shouldThrowNoSuchElement(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.deleteUser(1);
    }
}
