package com.nnk.springboot.controllers.rest;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
import com.nnk.springboot.service.CurvePointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/curvePointRest")
@CrossOrigin("http://localhost:8080")
public class CurvePointRest {
    String requestMapping = "/curvePointRest/";

    @Autowired
    public CurvePointService service;

    @GetMapping("")
    public List<CurvePoint> getAllCurvePoint(){
        log.info("GET "+requestMapping);
        return service.getAllCurvePoint();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurvePoint> getCurvePoint(@PathVariable("id") final Integer id){
        log.info("GET "+requestMapping+id);
        try {
            return ResponseEntity.ok(service.getCurvePoint(id));
        } catch (NoSuchElementException e){
            log.error("GET "+requestMapping+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<CurvePoint> addCurvePoint(@RequestBody CurvePoint domain){
        log.info("POST "+requestMapping+domain.getId());
        try {
            return ResponseEntity.ok(service.addCurvePoint(domain));
        } catch (NoSuchElementException e){
            log.error("POST "+requestMapping+domain.getId()+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurvePoint> updateCurvePoint(@PathVariable("id") final Integer id, @RequestBody CurvePoint domain){
        log.info("PUT "+requestMapping+id);
        try {
            return ResponseEntity.ok(service.putCurvePoint(domain, id));
        } catch (NoSuchElementException e){
            log.error("PUT "+requestMapping+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CurvePoint> deleteCurvePoint(@PathVariable("id") final Integer id){
        log.info("DELETE "+requestMapping+id);
        try {
            return ResponseEntity.ok(service.deleteCurvePoint(id));
        }catch (NoSuchElementException e){
            log.error("DELETE "+requestMapping+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
