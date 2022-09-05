package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Controller
public class TradeController {
    String url = "trade";

    @Autowired
    private TradeService service;

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        model.addAttribute(url+"List", service.getAllTrade());
        return url+"/list";
    }

    @GetMapping("/trade/add")
    public String addTradeForm(Trade domain) {
        return url+"/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade domain, BindingResult result, Model model) {
        if (result.hasErrors()){
            return url+"/add";
        }
        service.addTrade(domain);
        model.addAttribute(url+"List", service.getAllTrade());
        return "redirect:/"+url+"/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get domain by Id and to model then show to the form
        model.addAttribute(url, service.getTrade(id));
        return url+"/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, Model model, @Valid Trade domain, BindingResult result) {
        if (result.hasErrors()) {
            return url+"/update";
        }
        service.putTrade(domain, id);
        model.addAttribute(url+"List", service.getAllTrade());
        return "redirect:/"+url+"/list";
    }

    @PostMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        model.addAttribute(url+"Delete", service.deleteTrade(id));
        return "redirect:/"+url+"/list";
    }
}
