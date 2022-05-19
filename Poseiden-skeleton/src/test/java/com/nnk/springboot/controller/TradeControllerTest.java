package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TradeController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TradeControllerTest {

    @Autowired
    public MockMvc mockMvc;
    @MockBean
    public TradeService service;

    @Test
    public void  TradeHomeListTest_ShouldReturn_Ok() throws Exception {
        when(service.getAllTrade()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/trade/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("tradeList"))
                .andExpect(view().name("trade/list"));
    }

    @Test
    public void  TradeAddBodFormTest_ShouldReturn_Ok() throws Exception {
        when(service.getAllTrade()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/trade/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"));
    }
    @Test
    public void  TradeValidateTest_ShouldReturn_Ok() throws Exception {
        when(service.getAllTrade()).thenReturn(new ArrayList<>());
        mockMvc.perform(post("/trade/validate"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"));
    }
    @Test
    public void  TradeShowUpdateFormTest_ShouldReturn_Ok() throws Exception {
        Trade t = new Trade();
        when(service.getTrade(any())).thenReturn(t);
        mockMvc.perform(get("/trade/update/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("trade"))
                .andExpect(view().name("trade/update"));
    }
    @Test
    public void  TradeUpdateBidTest_ShouldReturn_Ok() throws Exception {
        Trade t = new Trade();
        when(service.getTrade(any())).thenReturn(t);
        when(service.getAllTrade()).thenReturn(new ArrayList<>());
        mockMvc.perform(post("/trade/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"));
    }
    @Test
    public void  TradeDeleteBitTest_ShouldReturn_Ok() throws Exception {
        Trade t = new Trade();
        when(service.deleteTrade(any())).thenReturn(t);
        t.setTradeId(1);
        mockMvc.perform(post("/trade/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/trade/list"));
    }

}
