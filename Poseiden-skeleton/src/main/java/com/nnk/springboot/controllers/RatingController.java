package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
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
public class RatingController {
    String url = "rating";
    @Autowired
    private RatingService service;

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        model.addAttribute(url+"List", service.getAllRating());
        return url+"/list";
    }
    @GetMapping("/rating/add")
    public String addRatingForm(Rating domain) {
        return url+"/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating domain, BindingResult result, Model model) {
        if (result.hasErrors()){
            return url+"/add";
        }
        service.addRating(domain);
        model.addAttribute(url+"List", service.getAllRating());
        return "redirect:/"+url+"/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get domain by Id and to model then show to the form
        model.addAttribute(url, service.getRating(id));
        return url+"/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, Model model, @Valid Rating domain, BindingResult result) {
        if (result.hasErrors()) {
            return url+"/update";
        }
        service.putRating(domain, id);
        model.addAttribute(url+"List", service.getAllRating());
        return "redirect:/"+url+"/list";
    }

    @PostMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        model.addAttribute(url+"Delete", service.deleteRating(id));
        return "redirect:/"+url+"/list";
    }
}
