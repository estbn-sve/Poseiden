package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(CurveController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CurvePointControllerTest {

    @Autowired
    public MockMvc mockMvc;
    @MockBean
    public CurvePointService service;

    @Test
    public void  CurvePointHomeListTest_ShouldReturn_Ok() throws Exception {
        when(service.getAllCurvePoint()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("curvePointList"))
                .andExpect(view().name("curvePoint/list"));
    }

    @Test
    public void  CurvePointAddBodFormTest_ShouldReturn_Ok() throws Exception {
        when(service.getAllCurvePoint()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }
    @Test
    public void  CurvePointValidateTest_ShouldReturn_Ok() throws Exception {
        when(service.getAllCurvePoint()).thenReturn(new ArrayList<>());
        mockMvc.perform(post("/curvePoint/validate"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }
    @Test
    public void  CurvePointShowUpdateFormTest_ShouldReturn_Ok() throws Exception {
        CurvePoint t = new CurvePoint();
        when(service.getCurvePoint(any())).thenReturn(t);
        mockMvc.perform(get("/curvePoint/update/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("curvePoint"))
                .andExpect(view().name("curvePoint/update"));
    }
    @Test
    public void  CurvePointUpdateBidTest_ShouldReturn_Ok() throws Exception {
        CurvePoint t = new CurvePoint();
        when(service.getCurvePoint(any())).thenReturn(t);
        when(service.getAllCurvePoint()).thenReturn(new ArrayList<>());
        mockMvc.perform(post("/curvePoint/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"));
    }
    @Test
    public void  CurvePointDeleteBitTest_ShouldReturn_Ok() throws Exception {
        CurvePoint t = new CurvePoint();
        when(service.deleteCurvePoint(any())).thenReturn(t);
        t.setId(1);
        mockMvc.perform(post("/curvePoint/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/curvePoint/list"));
    }

}

