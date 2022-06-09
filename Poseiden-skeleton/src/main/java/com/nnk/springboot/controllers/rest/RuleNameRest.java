package com.nnk.springboot.controllers.rest;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/ruleNameRest")
@CrossOrigin("http://localhost:8080")
public class RuleNameRest {
    String requestMapping = "/ruleNameRest/";

    @Autowired
    public RuleNameService service;

    @GetMapping("")
    public List<RuleName> getAllRuleName(){
        log.info("GET "+requestMapping);
        return service.getAllRuleName();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RuleName> getRuleName(@PathVariable("id") final Integer id){
        log.info("GET "+requestMapping+id);
        try {
            return ResponseEntity.ok(service.getRuleName(id));
        } catch (NoSuchElementException e){
            log.error("GET "+requestMapping+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<RuleName> addRuleName(@RequestBody RuleName domain){
        log.info("POST "+requestMapping+domain.getId());
        try {
            return ResponseEntity.ok(service.addRuleName(domain));
        } catch (NoSuchElementException e){
            log.error("POST "+requestMapping+domain.getId()+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RuleName> updateRuleName(@PathVariable("id") final Integer id, @RequestBody RuleName domain){
        log.info("PUT "+requestMapping+id);
        try {
            return ResponseEntity.ok(service.putRuleName(domain, id));
        } catch (NoSuchElementException e){
            log.error("PUT "+requestMapping+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RuleName> deleteRuleName(@PathVariable("id") final Integer id){
        log.info("DELETE "+requestMapping+id);
        try {
            return ResponseEntity.ok(service.deleteRuleName(id));
        }catch (NoSuchElementException e){
            log.error("DELETE "+requestMapping+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
