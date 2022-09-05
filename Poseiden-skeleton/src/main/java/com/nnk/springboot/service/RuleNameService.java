package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RuleNameService {

    @Autowired
    private RuleNameRepository repository;

    public List<RuleName> getAllRuleName(){
        return repository.findAll();
    }

    public RuleName getRuleName(final Integer id){
        return repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with getRuleName"+id));
    }

    public RuleName addRuleName(RuleName domain){
        Integer id = domain.getId();
        if(id == null || !repository.existsById(id)){
            return repository.save(domain);
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with addRuleName"+id));
        }
    }

    public RuleName putRuleName(RuleName currentDomain, final Integer id){
        if(repository.existsById(id)){
            repository.save(currentDomain);
            return currentDomain;
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with putRuleName "+id));
        }
    }

    public RuleName deleteRuleName(final Integer id){
        RuleName domain = repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with deleteRuleName "+id));
        RuleName copy = RuleName.builder()
                .name(domain.getName())
                .description(domain.getDescription())
                .json(domain.getJson())
                .template(domain.getTemplate())
                .sqlStr(domain.getSqlStr())
                .sqlPart(domain.getSqlPart())
                .build();
        repository.delete(domain);
        return copy;
    }
}
