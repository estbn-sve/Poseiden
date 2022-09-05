package com.nnk.springboot.controller.rest;

import com.nnk.springboot.controllers.rest.TradeRest;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
@WebMvcTest(TradeRest.class)
@AutoConfigureMockMvc
public class TradeControllerRestTest {
    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public TradeService service;

    //getAll
    @Test
    @WithMockUser(value = "spring")
    public void getAllTrade() throws Exception{
        when(service.getAllTrade()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/tradeRest"))
                .andExpect(status().isOk());
    }
    //get
    @Test
    @WithMockUser(value = "spring")
    public void getTrade_shouldReturnOk() throws Exception{
        when(service.getTrade(any())).thenReturn(new Trade());
        mockMvc.perform(get("/tradeRest/1"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(value = "spring")
    public void getTrade_shouldReturnNotFound() throws Exception{
        when(service.getTrade(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(get("/tradeRest/1"))
                .andExpect(status().isNotFound());
    }
    //update
    @Test
    @WithMockUser(value = "spring")
    public void putTrade_shouldReturnOk() throws Exception{
        when(service.putTrade(any(),any())).thenReturn(new Trade());
        mockMvc.perform(put("/tradeRest/1").with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(value = "spring")
    public void putTrade_shouldReturnNotFound() throws Exception{
        when(service.putTrade(any(),any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(put("/tradeRest/1").with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }
    //add
    @Test
    @WithMockUser(value = "spring")
    public void addTrade_shouldReturnOk() throws Exception{
        when(service.getTrade(any())).thenReturn(new Trade());
        mockMvc.perform(post("/tradeRest").with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(value = "spring")
    public void addTrade_shouldReturnNotFound() throws Exception{
        when(service.addTrade(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(post("/tradeRest").with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }
    //delete
    @Test
    @WithMockUser(value = "spring")
    public void deleteTrade_shouldReturnOk() throws Exception{
        when(service.deleteTrade(any())).thenReturn(new Trade());
        mockMvc.perform(delete("/tradeRest/1").with(csrf()))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(value = "spring")
    public void deleteTrade_shouldReturnNotFound() throws Exception{
        when(service.deleteTrade(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(delete("/tradeRest/1").with(csrf()))
                .andExpect(status().isNotFound());
    }
}
