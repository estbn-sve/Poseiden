package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;


@Controller
public class BidListController {

    @Autowired
    private BidListService service;

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        model.addAttribute("bidListList", service.getAllBidList());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (result.hasErrors()){
            return "bidList/add";
        }
        service.addBidList(bid);
        model.addAttribute("bidListList", service.getAllBidList());
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("bidList", service.getBidList(id));
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, Model model, @Valid BidList bid, BindingResult result) {
        if (result.hasErrors()) {
            return "bidList/update";
        }
        service.putBidList(bid, id);
        model.addAttribute("bidListList", service.getAllBidList());
        return "redirect:/bidList/list";
    }

    @PostMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("bidListDelete", service.deleteBidList(id));
        return "redirect:/bidList/list";
    }
}
