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
        if (repository.existsById(id)){
            Trade domain = currentDomain;
            currentDomain = repository.findById(id).get();
            String account = domain.getAccount();
            if (account != null) {
                currentDomain.setAccount(account);
            }
            String type = domain.getType();
            if (type != null) {
                currentDomain.setType(type);
            }
            Double buyQuantity = domain.getBuyQuantity();
            if(buyQuantity!=null){
                currentDomain.setBuyQuantity(buyQuantity);
            }
            Double sellQuantity = domain.getSellQuantity();
            if(sellQuantity!=null){
                currentDomain.setSellQuantity(sellQuantity);
            }
            Double buyPrice = domain.getBuyPrice();
            if(buyPrice!=null){
                currentDomain.setBuyPrice(buyPrice);
            }
            Double sellPrice = domain.getSellPrice();
            if(sellPrice!=null){
                currentDomain.setSellPrice(sellPrice);
            }
            String benchmark = domain.getBenchmark();
            if (benchmark != null) {
                currentDomain.setBenchmark(benchmark);
            }
            Date tradeDate = domain.getTradeDate();
            if (tradeDate != null) {
                currentDomain.setTradeDate(tradeDate);
            }
            String security = domain.getSecurity();
            if (security != null) {
                currentDomain.setSecurity(security);
            }
            String status = domain.getStatus();
            if (status != null) {
                currentDomain.setStatus(status);
            }
            String trader = domain.getTrader();
            if (trader != null) {
                currentDomain.setTrader(trader);
            }
            String book = domain.getBook();
            if (book != null) {
                currentDomain.setBook(book);
            }
            String creationName = domain.getCreationName();
            if (creationName != null) {
                currentDomain.setCreationName(creationName);
            }
            Date creationDate = domain.getCreationDate();
            if (creationDate != null) {
                currentDomain.setCreationDate(creationDate);
            }
            String revisionName = domain.getRevisionName();
            if (revisionName != null) {
                currentDomain.setRevisionName(revisionName);
            }
            Date revisionDate = domain.getRevisionDate();
            if (revisionDate != null) {
                currentDomain.setRevisionDate(revisionDate);
            }
            String dealName = domain.getDealName();
            if (dealName != null) {
                currentDomain.setDealName(dealName);
            }
            String dealType = domain.getDealType();
            if (dealType != null) {
                currentDomain.setDealType(dealType);
            }
            String sourceListId = domain.getSourceListId();
            if (sourceListId != null) {
                currentDomain.setSourceListId(sourceListId);
            }
            String side = domain.getSide();
            if (side != null) {
                currentDomain.setSide(side);
            }
            return repository.save(currentDomain);
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
