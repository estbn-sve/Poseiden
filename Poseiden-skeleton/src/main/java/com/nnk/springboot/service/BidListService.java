package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BidListService {

    @Autowired
    private BidListRepository repository;

    public List<BidList> getAllBidList(){
        return repository.findAll();
    }

    public BidList getBidList(final Integer id){
        return repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with getBidList"+id));
    }

    public BidList addBidList(BidList bidList){
        Integer id = bidList.getBidListId();
        if(id == null || !repository.existsById(id)){
            return repository.save(bidList);
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with addBidList"+id));
        }
    }

    public BidList putBidList(BidList currentBidList, final Integer id){
        if(repository.existsById(id)){
            repository.save(currentBidList);
            return currentBidList;
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with putBidList "+id));
        }
    }

    public BidList deleteBidList(final Integer id){
        BidList bl = repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with deleteBidList "+id));
        BidList copy = BidList.builder()
                .BidListId(bl.getBidListId())
                .account(bl.getAccount())
                .type(bl.getType())
                .bidQuantity(bl.getBidQuantity())
                .askQuantity(bl.getAskQuantity())
                .bid(bl.getBid())
                .ask(bl.getAsk())
                .benchmark(bl.getBenchmark())
                .bidListDate(bl.getBidListDate())
                .commentary(bl.getCommentary())
                .security(bl.getSecurity())
                .status(bl.getStatus())
                .trader(bl.getTrader())
                .book(bl.getBook())
                .creationName(bl.getCreationName())
                .creationDate(bl.getCreationDate())
                .revisionName(bl.getRevisionName())
                .revisionDate(bl.getRevisionDate())
                .dealName(bl.getDealName())
                .dealType(bl.getDealType())
                .sourceListId(bl.getSourceListId())
                .side(bl.getSide())
                .build();
        repository.delete(bl);
        return copy;
    }

}
