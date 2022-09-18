package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class CurveController {
    String url = "curvePoint";
    @Autowired
    private CurvePointService service;

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        model.addAttribute(url+"List", service.getAllCurvePoint());
        return url+"/list";
    }

    @GetMapping("/curvePoint/add")
    public String addcurvePointForm(CurvePoint domain) {
        return url+"/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint domain, BindingResult result, Model model) {
        if (result.hasErrors()){
            return url+"/add";
        }
        service.addCurvePoint(domain);
        model.addAttribute(url+"List", service.getAllCurvePoint());
        return "redirect:/"+url+"/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute(url, service.getCurvePoint(id));
        return url+"/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updatecurvePoint(@PathVariable("id") Integer id, Model model, @Valid CurvePoint domain, BindingResult result) {
        if (result.hasErrors()) {
            return url+"/update";
        }
        service.putCurvePoint(domain, id);
        model.addAttribute(url+"List", service.getAllCurvePoint());
        return "redirect:/"+url+"/list";
    }

    @PostMapping("/curvePoint/delete/{id}")
    public String deletecurvePoint(@PathVariable("id") Integer id, Model model) {
        model.addAttribute(url+"Delete", service.deleteCurvePoint(id));
        return "redirect:/"+url+"/list";
    }
}
