package com.nnk.springboot.controllers.rest;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
import com.nnk.springboot.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/tradeRest")
@CrossOrigin("http://localhost:8080")
public class TradeRest {
    String requestMapping = "/tradeRest/";

    @Autowired
    public TradeService service;

    @GetMapping("")
    public List<Trade> getAllTrade(){
        log.info("GET "+requestMapping);
        return service.getAllTrade();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trade> getTrade(@PathVariable("id") final Integer id){
        log.info("GET "+requestMapping+id);
        try {
            return ResponseEntity.ok(service.getTrade(id));
        } catch (NoSuchElementException e){
            log.error("GET "+requestMapping+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Trade> addTrade(@RequestBody Trade domain){
        log.info("POST "+requestMapping+domain.getTradeId());
        try {
            return ResponseEntity.ok(service.addTrade(domain));
        } catch (NoSuchElementException e){
            log.error("POST "+requestMapping+domain.getTradeId()+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trade> updateTrade(@PathVariable("id") final Integer id, @RequestBody Trade domain){
        log.info("PUT "+requestMapping+id);
        try {
            return ResponseEntity.ok(service.putTrade(domain, id));
        } catch (NoSuchElementException e){
            log.error("PUT "+requestMapping+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Trade> deleteTrade(@PathVariable("id") final Integer id){
        log.info("DELETE "+requestMapping+id);
        try {
            return ResponseEntity.ok(service.deleteTrade(id));
        }catch (NoSuchElementException e){
            log.error("DELETE "+requestMapping+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
