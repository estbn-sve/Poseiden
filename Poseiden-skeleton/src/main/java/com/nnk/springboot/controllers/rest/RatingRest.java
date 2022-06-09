package com.nnk.springboot.controllers.rest;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/ratingRest")
@CrossOrigin("http://localhost:8080")
public class RatingRest {
    String requestMapping = "/ratingRest/";

    @Autowired
    public RatingService service;

    @GetMapping("")
    public List<Rating> getAllRating(){
        log.info("GET "+requestMapping);
        return service.getAllRating();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRating(@PathVariable("id") final Integer id){
        log.info("GET "+requestMapping+id);
        try {
            return ResponseEntity.ok(service.getRating(id));
        } catch (NoSuchElementException e){
            log.error("GET "+requestMapping+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Rating> addRating(@RequestBody Rating domain){
        log.info("POST "+requestMapping+domain.getId());
        try {
            return ResponseEntity.ok(service.addRating(domain));
        } catch (NoSuchElementException e){
            log.error("POST "+requestMapping+domain.getId()+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable("id") final Integer id, @RequestBody Rating domain){
        log.info("PUT "+requestMapping+id);
        try {
            return ResponseEntity.ok(service.putRating(domain, id));
        } catch (NoSuchElementException e){
            log.error("PUT "+requestMapping+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Rating> deleteRating(@PathVariable("id") final Integer id){
        log.info("DELETE "+requestMapping+id);
        try {
            return ResponseEntity.ok(service.deleteRating(id));
        }catch (NoSuchElementException e){
            log.error("DELETE "+requestMapping+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
