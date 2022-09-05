package com.nnk.springboot.controller.rest;

import com.nnk.springboot.controllers.rest.BidListRest;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(BidListRest.class)
//@AutoConfigureMockMvc
@ContextConfiguration
public class BidListControllerRestTest {
    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public BidListService service;

    //getAll
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void getAllBidList() throws Exception{
        when(service.getAllBidList()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/bidListRest"))
                .andExpect(status().isOk());
    }
    //get
    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN", "USER" })
    public void getBidList_shouldReturnOk() throws Exception{
        when(service.getBidList(any())).thenReturn(new BidList());
        mockMvc.perform(get("/bidListRest/1"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN", "USER" })
    public void getBidList_shouldReturnNotFound() throws Exception{
        when(service.getBidList(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(get("/bidListRest/1"))
                .andExpect(status().isNotFound());
    }
    //update
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void putBidList_shouldReturnOk() throws Exception{
        when(service.putBidList(any(),any())).thenReturn(new BidList());
        mockMvc.perform(put("/bidListRest/1").with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN", "USER" })
    public void putBidList_shouldReturnNotFound() throws Exception{
        when(service.putBidList(any(),any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(put("/bidListRest/1").with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }
    //add
    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN", "USER" })
    public void addBidList_shouldReturnOk() throws Exception{
        when(service.getBidList(any())).thenReturn(new BidList());
        mockMvc.perform(post("/bidListRest").with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN", "USER" })
    public void addBidList_shouldReturnNotFound() throws Exception{
        when(service.addBidList(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(post("/bidListRest").with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }
    //delete
    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN", "USER" })
    public void deleteBidList_shouldReturnOk() throws Exception{
        when(service.deleteBidList(any())).thenReturn(new BidList());
        mockMvc.perform(delete("/bidListRest/1").with(csrf()))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN", "USER" })
    public void deleteBidList_shouldReturnNotFound() throws Exception{
        when(service.deleteBidList(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(delete("/bidListRest/1").with(csrf()))
                .andExpect(status().isNotFound());
    }
}