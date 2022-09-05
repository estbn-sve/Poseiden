package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
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
@WebMvcTest(RuleNameController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RuleNameControllerTest {

    @Autowired
    public MockMvc mockMvc;
    @MockBean
    public RuleNameService service;

    @Test
    @WithMockUser(value = "spring")
    public void  RuleNameHomeListTest_ShouldReturn_Ok() throws Exception {
        when(service.getAllRuleName()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ruleNameList"))
                .andExpect(view().name("ruleName/list"));
    }

    @Test
    @WithMockUser(value = "spring")
    public void  RuleNameAddBodFormTest_ShouldReturn_Ok() throws Exception {
        when(service.getAllRuleName()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }
    @Test
    @WithMockUser(value = "spring")
    public void  RuleNameValidateTest_ShouldReturn_Ok() throws Exception {
        when(service.getAllRuleName()).thenReturn(new ArrayList<>());
        mockMvc.perform(post("/ruleName/validate"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }
    @Test
    @WithMockUser(value = "spring")
    public void  RuleNameShowUpdateFormTest_ShouldReturn_Ok() throws Exception {
        RuleName t = new RuleName();
        when(service.getRuleName(any())).thenReturn(t);
        mockMvc.perform(get("/ruleName/update/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ruleName"))
                .andExpect(view().name("ruleName/update"));
    }
    @Test
    @WithMockUser(value = "spring")
    public void  RuleNameUpdateBidTest_ShouldReturn_Ok() throws Exception {
        RuleName t = new RuleName();
        when(service.getRuleName(any())).thenReturn(t);
        when(service.getAllRuleName()).thenReturn(new ArrayList<>());
        mockMvc.perform(post("/ruleName/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"));
    }
    @Test
    @WithMockUser(value = "spring")
    public void  RuleNameDeleteBitTest_ShouldReturn_Ok() throws Exception {
        RuleName t = new RuleName();
        when(service.deleteRuleName(any())).thenReturn(t);
        t.setId(1);
        mockMvc.perform(post("/ruleName/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/ruleName/list"));
    }

}
