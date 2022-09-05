package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
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
public class RuleNameController {
    String url = "ruleName";
    @Autowired
    private RuleNameService service
            ;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        model.addAttribute(url+"List", service.getAllRuleName());
        return url+"/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleNameForm(RuleName domain) {
        return url+"/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName domain, BindingResult result, Model model) {
        if (result.hasErrors()){
            return url+"/add";
        }
        service.addRuleName(domain);
        model.addAttribute(url+"List", service.getAllRuleName());
        return "redirect:/"+url+"/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get domain by Id and to model then show to the form
        model.addAttribute(url, service.getRuleName(id));
        return url+"/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, Model model, @Valid RuleName domain, BindingResult result) {
        if (result.hasErrors()) {
            return url+"/update";
        }
        service.putRuleName(domain, id);
        model.addAttribute(url+"List", service.getAllRuleName());
        return "redirect:/"+url+"/list";
    }

    @PostMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        model.addAttribute(url+"Delete", service.deleteRuleName(id));
        return "redirect:/"+url+"/list";
    }
}
