package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
public class TradeServiceTest {
    @InjectMocks
    private TradeService service;

    @Mock
    private TradeRepository repository;

    //all
    @Test
    @WithMockUser(value = "spring")
    public void getAllTrade(){
        List<Trade> Trades = new ArrayList<>();
        when(repository.findAll()).thenReturn(Trades);
        assertEquals(service.getAllTrade(),Trades);
    }

    //get
    @Test
    @WithMockUser(value = "spring")
    public void getTrade_shouldReturnOk(){
        Trade domain = new Trade();
        when(repository.findById(any())).thenReturn(Optional.of(domain));
        assertEquals(service.getTrade(1),domain);
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void getTrade_shouldThrowNoSuchElement(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.getTrade(1);
    }
    //update
    @Test
    @WithMockUser(value = "spring")
    public void putTrade_shouldReturnOk(){
        Trade domain = new Trade();
        when(repository.findById(any())).thenReturn(Optional.of(domain));
        assertEquals(service.putTrade(domain,1),domain);
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void putTrade_shouldThrowNoSuchElement(){
        Trade domain = new Trade();
        when(repository.existsById(any())).thenReturn(false);
        service.putTrade(domain,1);
    }
    //add
    @Test
    @WithMockUser(value = "spring")
    public void addTrade_shouldReturnOk(){
        Trade domain = new Trade();
        domain.setTradeId(1);
        when(repository.existsById(any())).thenReturn(false);
        when(repository.save(any())).thenReturn(domain);
        assertEquals(service.addTrade(domain),domain);
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void addTrade_shouldThrowNoSuchElement(){
        Trade domain = new Trade();
        domain.setTradeId(1);
        when(repository.existsById(any())).thenReturn(true);
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.addTrade(domain);
    }
    //delete
    @Test
    @WithMockUser(value = "spring")
    public void deleteTrade_shouldReturnOk(){
        Trade domain = new Trade();
        domain.setType("test");
        when(repository.findById(any())).thenReturn(Optional.of(domain));
        doNothing().when(repository).delete(any());
        Trade domainResult = service.deleteTrade(1);
        assertEquals(domain.getType(), domainResult.getType());
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void deleteTrade_shouldThrowNoSuchElement(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.deleteTrade(1);
    }
}
