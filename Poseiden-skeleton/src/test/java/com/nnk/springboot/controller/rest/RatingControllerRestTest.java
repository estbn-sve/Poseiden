package com.nnk.springboot.controller.rest;

import com.nnk.springboot.controllers.rest.RatingRest;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
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
@WebMvcTest(RatingRest.class)
@AutoConfigureMockMvc
public class RatingControllerRestTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public RatingService service;

    //getAll
    @Test
    public void getAllRating() throws Exception{
        when(service.getAllRating()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/ratingRest"))
                .andExpect(status().isOk());
    }
    //get
    @Test
    public void getRating_shouldReturnOk() throws Exception{
        when(service.getRating(any())).thenReturn(new Rating());
        mockMvc.perform(get("/ratingRest/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void getRating_shouldReturnNotFound() throws Exception{
        when(service.getRating(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(get("/ratingRest/1"))
                .andExpect(status().isNotFound());
    }
    //update
    @Test
    public void putRating_shouldReturnOk() throws Exception{
        when(service.putRating(any(),any())).thenReturn(new Rating());
        mockMvc.perform(put("/ratingRest/1").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }
    @Test
    public void putRating_shouldReturnNotFound() throws Exception{
        when(service.putRating(any(),any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(put("/ratingRest/1").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }
    //add
    @Test
    public void addRating_shouldReturnOk() throws Exception{
        when(service.getRating(any())).thenReturn(new Rating());
        mockMvc.perform(post("/ratingRest").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }
    @Test
    public void addRating_shouldReturnNotFound() throws Exception{
        when(service.addRating(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(post("/ratingRest").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }
    //delete
    @Test
    public void deleteRating_shouldReturnOk() throws Exception{
        when(service.deleteRating(any())).thenReturn(new Rating());
        mockMvc.perform(delete("/ratingRest/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteRating_shouldReturnNotFound() throws Exception{
        when(service.deleteRating(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(delete("/ratingRest/1"))
                .andExpect(status().isNotFound());
    }
}

