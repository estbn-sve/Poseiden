package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CurvePointService {
    @Autowired
    private CurvePointRepository repository;

    public List<CurvePoint> getAllCurvePoint(){
        return repository.findAll();
    }

    public CurvePoint getCurvePoint(final Integer id){
        return repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with getCurvePoint"+id));
    }

    public CurvePoint addCurvePoint(CurvePoint domain){
        Integer id = domain.getId();
        if(id == null || !repository.existsById(id)){
            return repository.save(domain);
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with addCurvePoint"+id));
        }
    }

    public CurvePoint putCurvePoint(CurvePoint currentDomain, final Integer id){
        if(repository.existsById(id)){
            repository.save(currentDomain);
            return currentDomain;
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with putCurvePoint "+id));
        }
    }

    public CurvePoint deleteCurvePoint(final Integer id){
        CurvePoint domain = repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with deleteCurvePoint "+id));
        CurvePoint copy = CurvePoint.builder()
                .curveId(domain.getCurveId())
                .asOfDate(domain.getAsOfDate())
                .term(domain.getTerm())
                .value(domain.getValue())
                .creationDate(domain.getCreationDate())
                .build();
        repository.delete(domain);
        return copy;
    }
}
