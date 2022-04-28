package com.nnk.springboot.controller.rest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BidListControllerRestTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidListService service;

    //getAll
    @Test
    public void getAllBidList() throws Exception{
        when(service.getAllBidList()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/bidListRest"))
                .andExpect(status().isOk());
    }
    //get
    @Test
    public void getBidList_shouldReturnOk() throws Exception{
        when(service.getBidList(any())).thenReturn(new BidList());
        mockMvc.perform(get("/bidListRest/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void getBidList_shouldReturnNotFound() throws Exception{
        when(service.getBidList(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(get("/bidListRest/1"))
                .andExpect(status().isNotFound());
    }
    //update
    @Test
    public void putBidList_shouldReturnOk() throws Exception{
        when(service.putBidList(any(),any())).thenReturn(new BidList());
        mockMvc.perform(put("/bidListRest/1").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }
    @Test
    public void putBidList_shouldReturnNotFound() throws Exception{
        when(service.putBidList(any(),any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(put("/bidListRest/1").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }
    //add
    @Test
    public void addBidList_shouldReturnOk() throws Exception{
        when(service.getBidList(any())).thenReturn(new BidList());
        mockMvc.perform(post("/bidListRest").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }
    @Test
    public void addBidList_shouldReturnNotFound() throws Exception{
        when(service.addBidList(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(post("/bidListRest").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }
    //delete
    @Test
    public void deleteBidList_shouldReturnOk() throws Exception{
        when(service.deleteBidList(any())).thenReturn(new BidList());
        mockMvc.perform(delete("/bidListRest/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteBidList_shouldReturnNotFound() throws Exception{
        when(service.deleteBidList(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(delete("/bidListRest/1"))
                .andExpect(status().isNotFound());
    }
}
