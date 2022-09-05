package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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
public class CurvePointServiceTest {

    @InjectMocks
    private CurvePointService service;

    @Mock
    private CurvePointRepository repository;

    //all
    @Test
    @WithMockUser(value = "spring")
    public void getAllCurvePoint(){
        List<CurvePoint> CurvePoints = new ArrayList<>();
        when(repository.findAll()).thenReturn(CurvePoints);
        assertEquals(service.getAllCurvePoint(),CurvePoints);
    }

    //get
    @Test
    @WithMockUser(value = "spring")
    public void getCurvePoint_shouldReturnOk(){
        CurvePoint model = new CurvePoint();
        when(repository.findById(any())).thenReturn(Optional.of(model));
        assertEquals(service.getCurvePoint(1),model);
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void getCurvePoint_shouldThrowNoSuchElement(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.getCurvePoint(1);
    }
    //update
    @Test
    @WithMockUser(value = "spring")
    public void putCurvePoint_shouldReturnOk(){
        CurvePoint model = new CurvePoint();
        when(repository.findById(any())).thenReturn(Optional.of(model));
        assertEquals(service.putCurvePoint(model,1),model);
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void putCurvePoint_shouldThrowNoSuchElement(){
        CurvePoint model = new CurvePoint();
        when(repository.existsById(any())).thenReturn(false);
        service.putCurvePoint(model,1);
    }
    //add
    @Test
    @WithMockUser(value = "spring")
    public void addCurvePoint_shouldReturnOk(){
        CurvePoint model = new CurvePoint();
        model.setId(1);
        when(repository.existsById(any())).thenReturn(false);
        when(repository.save(any())).thenReturn(model);
        assertEquals(service.addCurvePoint(model),model);
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void addCurvePoint_shouldThrowNoSuchElement(){
        CurvePoint model = new CurvePoint();
        model.setId(1);
        when(repository.existsById(any())).thenReturn(true);
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.addCurvePoint(model);
    }
    //delete
    @Test
    @WithMockUser(value = "spring")
    public void deleteCurvePoint_shouldReturnOk(){
        CurvePoint model = new CurvePoint();
        model.setCurveId(2);
        when(repository.findById(any())).thenReturn(Optional.of(model));
        doNothing().when(repository).delete(any());
        CurvePoint modelResult = service.deleteCurvePoint(1);
        assertEquals(model.getCurveId(), modelResult.getCurveId());
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void deleteCurvePoint_shouldThrowNoSuchElement(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.deleteCurvePoint(1);
    }
}
