package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(RatingController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RatingControllerTest {

    @Autowired
    public MockMvc mockMvc;
    @MockBean
    public RatingService service;

    @Test
    @WithMockUser(value = "spring")
    public void  RatingHomeListTest_ShouldReturn_Ok() throws Exception {
        when(service.getAllRating()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ratingList"))
                .andExpect(view().name("rating/list"));
    }

    @Test
    @WithMockUser(value = "spring")
    public void  RatingAddBodFormTest_ShouldReturn_Ok() throws Exception {
        when(service.getAllRating()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }
    @Test
    @WithMockUser(value = "spring")
    public void  RatingValidateTest_ShouldReturn_Ok() throws Exception {
        when(service.getAllRating()).thenReturn(new ArrayList<>());
        mockMvc.perform(post("/rating/validate"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }
    @Test
    @WithMockUser(value = "spring")
    public void  RatingShowUpdateFormTest_ShouldReturn_Ok() throws Exception {
        Rating t = new Rating();
        when(service.getRating(any())).thenReturn(t);
        mockMvc.perform(get("/rating/update/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rating"))
                .andExpect(view().name("rating/update"));
    }
    @Test
    @WithMockUser(value = "spring")
    public void  RatingUpdateBidTest_ShouldReturn_Ok() throws Exception {
        Rating t = new Rating();
        when(service.getRating(any())).thenReturn(t);
        when(service.getAllRating()).thenReturn(new ArrayList<>());
        mockMvc.perform(post("/rating/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"));
    }
    @Test
    @WithMockUser(value = "spring")
    public void  RatingDeleteBitTest_ShouldReturn_Ok() throws Exception {
        Rating t = new Rating();
        when(service.deleteRating(any())).thenReturn(t);
        t.setId(1);
        mockMvc.perform(post("/rating/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/rating/list"));
    }

}

