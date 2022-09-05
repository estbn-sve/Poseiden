package com.nnk.springboot.service;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BidListServiceTest {

    @InjectMocks
    private BidListService service;

    @Mock
    private BidListRepository repository;

    //all
    @Test
    @WithMockUser(value = "spring")
    public void getAllBidList(){
        List<BidList> bidLists = new ArrayList<>();
        when(repository.findAll()).thenReturn(bidLists);
        assertEquals(service.getAllBidList(),bidLists);
    }

    //get
    @Test
    @WithMockUser(value = "spring")
    public void getBidList_shouldReturnOk(){
        BidList bid = new BidList();
        when(repository.findById(any())).thenReturn(Optional.of(bid));
        assertEquals(service.getBidList(1),bid);
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void getBidList_shouldThrowNoSuchElement(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.getBidList(1);
    }
    //update
    @Test
    @WithMockUser(value = "spring")
    public void putBidList_shouldReturnOk(){
        BidList bid = new BidList();
        bid.setBidListId(1);
        bid.setBid(10.00);
        bid.setSide("Side");
        bid.setAccount("Account");
        bid.setSourceListId("SourceListId");
        bid.setDealType("DealType");
        bid.setType("type");
        bid.setRevisionName("RevisionName");
        bid.setStatus("status");
        when(repository.existsById(any())).thenReturn(true);
        when(repository.findById(any())).thenReturn(Optional.of(bid));
        assertEquals(service.putBidList(bid,1),bid);
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void putBidList_shouldThrowNoSuchElement(){
        BidList bid = new BidList();
        when(repository.existsById(any())).thenReturn(false);
        service.putBidList(bid,1);
    }
    //add
    @Test
    @WithMockUser(value = "spring")
    public void addBidList_shouldReturnOk(){
        BidList bid = new BidList();
        bid.setBidListId(1);
        when(repository.existsById(any())).thenReturn(false);
        when(repository.save(any())).thenReturn(bid);
        assertEquals(service.addBidList(bid),bid);
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void addBidList_shouldThrowNoSuchElement(){
        BidList bid = new BidList();
        bid.setBidListId(1);
        when(repository.existsById(any())).thenReturn(true);
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.addBidList(bid);
    }
    //delete
    @Test
    @WithMockUser(value = "spring")
    public void deleteBidList_shouldReturnOk(){
        BidList bid = new BidList();
        bid.setType("test");
        when(repository.findById(any())).thenReturn(Optional.of(bid));
        doNothing().when(repository).delete(any());
        BidList bidResult = service.deleteBidList(1);
        assertEquals(bid.getType(), bidResult.getType());
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void deleteBidList_shouldThrowNoSuchElement(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.deleteBidList(1);
    }
}
