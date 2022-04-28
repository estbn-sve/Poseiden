package com.nnk.springboot.service;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class BidListServiceTest {

    @InjectMocks
    private BidListService service;

    @Mock
    private BidListRepository repository;

    //all
    @Test
    public void getAllBidList(){
        List<BidList> bidLists = new ArrayList<>();
        when(repository.findAll()).thenReturn(bidLists);
        assertEquals(service.getAllBidList(),bidLists);
    }

    //get
    @Test
    public void getBidList_shouldReturnOk(){
        BidList bid = new BidList();
        when(repository.findById(any())).thenReturn(Optional.of(bid));
        assertEquals(service.getBidList(1),bid);
    }
    @Test
    public void getBidList_shouldThrowNoSuchElement(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class,()->service.getBidList(1));
    }
    //update
    @Test
    public void putBidList_shouldReturnOk(){
        BidList bid = new BidList();
        when(repository.findById(any())).thenReturn(Optional.of(bid));
        when(repository.existsById(any())).thenReturn(true);
        assertEquals(service.putBidList(bid,1),bid);
    }
    @Test
    public void putBidList_shouldThrowNoSuchElement(){
        BidList bid = new BidList();
        when(repository.existsById(any())).thenReturn(false);
        assertThrows(NoSuchElementException.class,()->service.putBidList(bid,1));
    }
    //add
    @Test
    public void addBidList_shouldReturnOk(){
        BidList bid = new BidList();
        when(repository.findById(any())).thenReturn(Optional.of(bid));
        assertEquals(service.addBidList(bid),bid);
    }
    @Test
    public void addBidList_shouldThrowNoSuchElement(){
        BidList bid = new BidList();
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class,()->service.addBidList(bid));
    }
    //delete
    @Test
    public void deleteBidList_shouldReturnOk(){
        BidList bid = new BidList();
        bid.setType("test");
        when(repository.findById(any())).thenReturn(Optional.of(bid));
        doNothing().when(repository).delete(any());
        BidList bidResult = service.deleteBidList(1);
        assertEquals(bid.getType(), bidResult.getType());
    }
    @Test
    public void deleteBidList_shouldThrowNoSuchElement(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class,()->service.deleteBidList(1));
    }
}
