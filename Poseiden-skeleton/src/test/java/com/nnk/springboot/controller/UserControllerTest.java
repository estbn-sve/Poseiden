package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
    @Autowired
    public MockMvc mockMvc;
    @MockBean
    public UserService service;

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void  UserAddBodFormTest_ShouldReturn_Ok() throws Exception {
        when(service.getAllUser()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void  UserValidateTest_ShouldReturn_Ok() throws Exception {
        when(service.getAllUser()).thenReturn(new ArrayList<>());
        mockMvc.perform(post("/user/validate"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void  UserShowUpdateFormTest_ShouldReturn_Ok() throws Exception {
        User t = new User();
        when(service.getUser(any())).thenReturn(t);
        mockMvc.perform(get("/user/update/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("user/update"));
    }
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void  UserUpdateBidTest_ShouldReturn_Ok() throws Exception {
        User t = new User();
        when(service.getUser(any())).thenReturn(t);
        when(service.getAllUser()).thenReturn(new ArrayList<>());
        mockMvc.perform(post("/user/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/update"));
    }
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void  UserDeleteBitTest_ShouldReturn_Ok() throws Exception {
        User t = new User();
        when(service.deleteUser(any())).thenReturn(t);
        t.setId(1);
        mockMvc.perform(post("/user/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/list"));
    }

}
