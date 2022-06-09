package com.nnk.springboot.controller.rest;

import com.nnk.springboot.controllers.rest.RuleNameRest;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RuleNameRest.class)
@AutoConfigureMockMvc
public class RuleNameControllerRestTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public RuleNameService service;

    //getAll
    @Test
    public void getAllRuleName() throws Exception{
        when(service.getAllRuleName()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/ruleNameRest"))
                .andExpect(status().isOk());
    }
    //get
    @Test
    public void getRuleName_shouldReturnOk() throws Exception{
        when(service.getRuleName(any())).thenReturn(new RuleName());
        mockMvc.perform(get("/ruleNameRest/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void getRuleName_shouldReturnNotFound() throws Exception{
        when(service.getRuleName(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(get("/ruleNameRest/1"))
                .andExpect(status().isNotFound());
    }
    //update
    @Test
    public void putRuleName_shouldReturnOk() throws Exception{
        when(service.putRuleName(any(),any())).thenReturn(new RuleName());
        mockMvc.perform(put("/ruleNameRest/1").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }
    @Test
    public void putRuleName_shouldReturnNotFound() throws Exception{
        when(service.putRuleName(any(),any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(put("/ruleNameRest/1").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }
    //add
    @Test
    public void addRuleName_shouldReturnOk() throws Exception{
        when(service.getRuleName(any())).thenReturn(new RuleName());
        mockMvc.perform(post("/ruleNameRest").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }
    @Test
    public void addRuleName_shouldReturnNotFound() throws Exception{
        when(service.addRuleName(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(post("/ruleNameRest").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }
    //delete
    @Test
    public void deleteRuleName_shouldReturnOk() throws Exception{
        when(service.deleteRuleName(any())).thenReturn(new RuleName());
        mockMvc.perform(delete("/ruleNameRest/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteRuleName_shouldReturnNotFound() throws Exception{
        when(service.deleteRuleName(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(delete("/ruleNameRest/1"))
                .andExpect(status().isNotFound());
    }
}

