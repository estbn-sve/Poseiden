package com.nnk.springboot.controllers.rest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/bidListRest")
@CrossOrigin("http://localhost:8080")
public class BidListRest {

    String requestMapping = "/bidListRest/";

    @Autowired
    private BidListService service;

    @GetMapping("")
    public List<BidList> getAllBidList(){
        log.info("GET "+requestMapping);
        return service.getAllBidList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BidList> getBidList(@PathVariable("id") final Integer id){
        log.info("GET "+requestMapping+id);
        try {
            return ResponseEntity.ok(service.getBidList(id));
        } catch (NoSuchElementException e){
            log.error("GET "+requestMapping+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<BidList> addBidList(@RequestBody BidList bidList){
        log.info("POST "+requestMapping+bidList.getBidListId());
        try {
            return ResponseEntity.ok(service.addBidList(bidList));
        } catch (NoSuchElementException e){
            log.error("POST "+requestMapping+bidList.getBidListId()+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BidList> updateBidList(@PathVariable("id") final Integer id, @RequestBody BidList bidList){
        log.info("PUT "+requestMapping+id);
        try {
            return ResponseEntity.ok(service.putBidList(bidList, id));
        } catch (NoSuchElementException e){
            log.error("PUT "+requestMapping+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BidList> deleteBidList(@PathVariable("id") final Integer id){
        log.info("DELETE "+requestMapping+id);
        try {
            return ResponseEntity.ok(service.deleteBidList(id));
        }catch (NoSuchElementException e){
            log.error("DELETE "+requestMapping+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

}
