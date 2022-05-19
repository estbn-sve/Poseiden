package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.controllers.rest.TradeRest;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.BidListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BidListController.class)
@AutoConfigureMockMvc(addFilters = false)
public class BidListControllerTest {
//    FormURLENCODED

    @Autowired
    public MockMvc mockMvc;
    @MockBean
    public BidListService service;

    @Test
    public void  bidListHomeListTest_ShouldReturn_Ok() throws Exception {
        when(service.getAllBidList()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bidListList"))
                .andExpect(view().name("bidList/list"));
    }

    @Test
    public void  bidListAddBodFormTest_ShouldReturn_Ok() throws Exception {
        when(service.getAllBidList()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }
    @Test
    public void  bidListValidateTest_ShouldReturn_Ok() throws Exception {
        when(service.getAllBidList()).thenReturn(new ArrayList<>());
        mockMvc.perform(post("/bidList/validate"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }
    @Test
    public void  bidListShowUpdateFormTest_ShouldReturn_Ok() throws Exception {
        BidList b = new BidList();
        when(service.getBidList(any())).thenReturn(b);
                mockMvc.perform(get("/bidList/update/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bidList"))
                .andExpect(view().name("bidList/update"));
    }
    @Test
    public void  bidListUpdateBidTest_ShouldReturn_Ok() throws Exception {
        BidList b = new BidList();
        when(service.getBidList(any())).thenReturn(b);
        when(service.getAllBidList()).thenReturn(new ArrayList<>());
        mockMvc.perform(post("/bidList/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"));
    }
    @Test
    public void  bidListDeleteBitTest_ShouldReturn_Ok() throws Exception {
        BidList b = new BidList();
        when(service.deleteBidList(any())).thenReturn(b);
        b.setBidListId(1);
        mockMvc.perform(post("/bidList/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/bidList/list"));
    }


}
