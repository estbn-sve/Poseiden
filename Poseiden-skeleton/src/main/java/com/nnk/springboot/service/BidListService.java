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
        if (repository.existsById(id)){
            BidList bidList = currentBidList;
            currentBidList = repository.findById(id).get();
            String creationName = bidList.getCreationName();
            if (creationName != null) {
                currentBidList.setCreationName(creationName);
            }
            String dealName = bidList.getDealName();
            if (dealName != null) {
                currentBidList.setDealName(dealName);
            }
            String account = bidList.getAccount();
            if (account != null) {
                currentBidList.setAccount(account);
            }
            String type = bidList.getType();
            if (type != null) {
                currentBidList.setType(type);
            }
            Double bigQuantity = bidList.getBidQuantity();
            if (bigQuantity != null) {
                currentBidList.setBidQuantity(bigQuantity);
            }
            Double askQuantity = bidList.getAskQuantity();
            if (askQuantity != null) {
                currentBidList.setAskQuantity(askQuantity);
            }
            Double bid = bidList.getBid();
            if (bid != null) {
                currentBidList.setBid(bid);
            }
            Double ask = bidList.getAsk();
            if (ask != null) {
                currentBidList.setAsk(ask);
            }
            String benchmark = bidList.getBenchmark();
            if (benchmark != null) {
                currentBidList.setBenchmark(benchmark);
            }
            Date bidListData = bidList.getBidListDate();
            if (bidListData != null) {
                currentBidList.setBidListDate(bidListData);
            }
            String commentary = bidList.getCommentary();
            if (commentary != null) {
                currentBidList.setCommentary(commentary);
            }
            String security = bidList.getSecurity();
            if (security != null) {
                currentBidList.setSecurity(security);
            }
            String status = bidList.getStatus();
            if (status != null) {
                currentBidList.setStatus(status);
            }
            String trader = bidList.getTrader();
            if (trader != null) {
                currentBidList.setTrader(trader);
            }
            String book = bidList.getBook();
            if (book != null) {
                currentBidList.setBook(book);
            }
            String revisionName = bidList.getRevisionName();
            if (revisionName != null) {
                currentBidList.setRevisionName(revisionName);
            }
            Date revisionDate = bidList.getRevisionDate();
            if (revisionDate != null) {
                currentBidList.setRevisionDate(revisionDate);
            }
            Date creationDate = bidList.getCreationDate();
            if (creationDate != null) {
                currentBidList.setCreationDate(creationDate);
            }
            String dealType = bidList.getDealType();
            if (dealType != null) {
                currentBidList.setDealType(dealType);
            }
            String sourceListId = bidList.getSourceListId();
            if (sourceListId != null) {
                currentBidList.setSourceListId(sourceListId);
            }
            String side = bidList.getSide();
            if (side != null) {
                currentBidList.setSide(side);
            }
            return repository.save(currentBidList);
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
