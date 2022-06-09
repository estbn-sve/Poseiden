package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    public void getAllCurvePoint(){
        List<CurvePoint> CurvePoints = new ArrayList<>();
        when(repository.findAll()).thenReturn(CurvePoints);
        assertEquals(service.getAllCurvePoint(),CurvePoints);
    }

    //get
    @Test
    public void getCurvePoint_shouldReturnOk(){
        CurvePoint model = new CurvePoint();
        when(repository.findById(any())).thenReturn(Optional.of(model));
        assertEquals(service.getCurvePoint(1),model);
    }
    @Test(expected = NoSuchElementException.class)
    public void getCurvePoint_shouldThrowNoSuchElement(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.getCurvePoint(1);
    }
    //update
    @Test
    public void putCurvePoint_shouldReturnOk(){
        CurvePoint model = new CurvePoint();
        when(repository.findById(any())).thenReturn(Optional.of(model));
        assertEquals(service.putCurvePoint(model,1),model);
    }
    @Test(expected = NoSuchElementException.class)
    public void putCurvePoint_shouldThrowNoSuchElement(){
        CurvePoint model = new CurvePoint();
        when(repository.existsById(any())).thenReturn(false);
        service.putCurvePoint(model,1);
    }
    //add
    @Test
    public void addCurvePoint_shouldReturnOk(){
        CurvePoint model = new CurvePoint();
        model.setId(1);
        when(repository.existsById(any())).thenReturn(false);
        when(repository.save(any())).thenReturn(model);
        assertEquals(service.addCurvePoint(model),model);
    }
    @Test(expected = NoSuchElementException.class)
    public void addCurvePoint_shouldThrowNoSuchElement(){
        CurvePoint model = new CurvePoint();
        model.setId(1);
        when(repository.existsById(any())).thenReturn(true);
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.addCurvePoint(model);
    }
    //delete
    @Test
    public void deleteCurvePoint_shouldReturnOk(){
        CurvePoint model = new CurvePoint();
        model.setCurveId(2);
        when(repository.findById(any())).thenReturn(Optional.of(model));
        doNothing().when(repository).delete(any());
        CurvePoint modelResult = service.deleteCurvePoint(1);
        assertEquals(model.getCurveId(), modelResult.getCurveId());
    }
    @Test(expected = NoSuchElementException.class)
    public void deleteCurvePoint_shouldThrowNoSuchElement(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.deleteCurvePoint(1);
    }
}
