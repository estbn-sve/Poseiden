package com.nnk.springboot.controller.rest;

import com.nnk.springboot.controllers.rest.CurvePointRest;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CurvePointRest.class)
@AutoConfigureMockMvc
public class CurvePointControllerRestTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public CurvePointService service;

    //getAll
    @Test
    @WithMockUser(value = "spring")
    public void getAllCurvePoint() throws Exception{
        when(service.getAllCurvePoint()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/curvePointRest"))
                .andExpect(status().isOk());
    }
    //get
    @Test
    @WithMockUser(value = "spring")
    public void getCurvePoint_shouldReturnOk() throws Exception{
        when(service.getCurvePoint(any())).thenReturn(new CurvePoint());
        mockMvc.perform(get("/curvePointRest/1"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(value = "spring")
    public void getCurvePoint_shouldReturnNotFound() throws Exception{
        when(service.getCurvePoint(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(get("/curvePointRest/1"))
                .andExpect(status().isNotFound());
    }
    //update
    @Test
    @WithMockUser(value = "spring")
    public void putCurvePoint_shouldReturnOk() throws Exception{
        when(service.putCurvePoint(any(),any())).thenReturn(new CurvePoint());
        mockMvc.perform(put("/curvePointRest/1").with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(value = "spring")
    public void putCurvePoint_shouldReturnNotFound() throws Exception{
        when(service.putCurvePoint(any(),any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(put("/curvePointRest/1").with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }
    //add
    @Test
    @WithMockUser(value = "spring")
    public void addCurvePoint_shouldReturnOk() throws Exception{
        when(service.getCurvePoint(any())).thenReturn(new CurvePoint());
        mockMvc.perform(post("/curvePointRest").with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(value = "spring")
    public void addCurvePoint_shouldReturnNotFound() throws Exception{
        when(service.addCurvePoint(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(post("/curvePointRest").with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }
    //delete
    @Test
    @WithMockUser(value = "spring")
    public void deleteCurvePoint_shouldReturnOk() throws Exception{
        when(service.deleteCurvePoint(any())).thenReturn(new CurvePoint());
        mockMvc.perform(delete("/curvePointRest/1").with(csrf()))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(value = "spring")
    public void deleteCurvePoint_shouldReturnNotFound() throws Exception{
        when(service.deleteCurvePoint(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(delete("/curvePointRest/1").with(csrf()))
                .andExpect(status().isNotFound());
    }
}