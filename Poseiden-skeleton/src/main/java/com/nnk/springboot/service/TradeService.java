package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TradeService {
    @Autowired
    private TradeRepository repository;

    public List<Trade> getAllTrade(){
        return repository.findAll();
    }

    public Trade getTrade(final Integer id){
        return repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with getTrade"+id));
    }

    public Trade addTrade(Trade domain){
        Integer id = domain.getTradeId();
        if(id == null || !repository.existsById(id)){
            return repository.save(domain);
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with addTrade"+id));
        }
    }

    public Trade putTrade(Trade currentDomain, final Integer id){
        if(repository.existsById(id)){
            repository.save(currentDomain);
            return currentDomain;
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with putTrade "+id));
        }
    }

    public Trade deleteTrade(final Integer id){
        Trade domain = repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with deleteTrade "+id));
        Trade copy = Trade.builder()
                .tradeId(domain.getTradeId())
                .account(domain.getAccount())
                .type(domain.getType())
                .buyQuantity(domain.getBuyQuantity())
                .sellQuantity(domain.getSellQuantity())
                .buyPrice(domain.getBuyPrice())
                .sellPrice(domain.getSellPrice())
                .benchmark(domain.getBenchmark())
                .tradeDate(domain.getTradeDate())
                .security(domain.getSecurity())
                .status(domain.getStatus())
                .trader(domain.getTrader())
                .book(domain.getBook())
                .creationName(domain.getCreationName())
                .creationDate(domain.getCreationDate())
                .revisionName(domain.getRevisionName())
                .revisionDate(domain.getRevisionDate())
                .dealName(domain.getDealName())
                .dealType(domain.getDealType())
                .sourceListId(domain.getSourceListId())
                .side(domain.getSide())
                .build();
        repository.delete(domain);
        return copy;
    }
}
