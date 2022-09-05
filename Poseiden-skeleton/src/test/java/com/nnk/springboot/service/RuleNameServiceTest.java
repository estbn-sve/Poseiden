package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
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
public class RuleNameServiceTest {

    @InjectMocks
    private RuleNameService service;

    @Mock
    private RuleNameRepository repository;

    //all
    @Test
    @WithMockUser(value = "spring")
    public void getAllRuleName(){
        List<RuleName> RuleNames = new ArrayList<>();
        when(repository.findAll()).thenReturn(RuleNames);
        assertEquals(service.getAllRuleName(),RuleNames);
    }

    //get
    @Test
    @WithMockUser(value = "spring")
    public void getRuleName_shouldReturnOk(){
        RuleName model = new RuleName();
        when(repository.findById(any())).thenReturn(Optional.of(model));
        assertEquals(service.getRuleName(1),model);
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void getRuleName_shouldThrowNoSuchElement(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.getRuleName(1);
    }
    //update
    @Test
    @WithMockUser(value = "spring")
    public void putRuleName_shouldReturnOk(){
        RuleName model = new RuleName();
        when(repository.findById(any())).thenReturn(Optional.of(model));
        assertEquals(service.putRuleName(model,1),model);
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void putRuleName_shouldThrowNoSuchElement(){
        RuleName model = new RuleName();
        when(repository.existsById(any())).thenReturn(false);
        service.putRuleName(model,1);
    }
    //add
    @Test
    @WithMockUser(value = "spring")
    public void addRuleName_shouldReturnOk(){
        RuleName model = new RuleName();
        model.setId(1);
        when(repository.existsById(any())).thenReturn(false);
        when(repository.save(any())).thenReturn(model);
        assertEquals(service.addRuleName(model),model);
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void addRuleName_shouldThrowNoSuchElement(){
        RuleName model = new RuleName();
        model.setId(1);
        when(repository.existsById(any())).thenReturn(true);
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.addRuleName(model);
    }
    //delete
    @Test
    @WithMockUser(value = "spring")
    public void deleteRuleName_shouldReturnOk(){
        RuleName model = new RuleName();
        model.setName("name");
        when(repository.findById(any())).thenReturn(Optional.of(model));
        doNothing().when(repository).delete(any());
        RuleName modelResult = service.deleteRuleName(1);
        assertEquals(model.getName(), modelResult.getName());
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void deleteRuleName_shouldThrowNoSuchElement(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.deleteRuleName(1);
    }
}
