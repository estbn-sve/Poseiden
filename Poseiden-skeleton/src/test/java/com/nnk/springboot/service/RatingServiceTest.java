package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.repositories.RatingRepository;
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
public class RatingServiceTest {

    @InjectMocks
    private RatingService service;

    @Mock
    private RatingRepository repository;

    //all
    @Test
    @WithMockUser(value = "spring")
    public void getAllRating(){
        List<Rating> Ratings = new ArrayList<>();
        when(repository.findAll()).thenReturn(Ratings);
        assertEquals(service.getAllRating(),Ratings);
    }

    //get
    @Test
    @WithMockUser(value = "spring")
    public void getRating_shouldReturnOk(){
        Rating model = new Rating();
        when(repository.findById(any())).thenReturn(Optional.of(model));
        assertEquals(service.getRating(1),model);
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void getRating_shouldThrowNoSuchElement(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.getRating(1);
    }
    //update
    @Test
    @WithMockUser(value = "spring")
    public void putRating_shouldReturnOk(){
        Rating model = new Rating();
        when(repository.findById(any())).thenReturn(Optional.of(model));
        assertEquals(service.putRating(model,1),model);
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void putRating_shouldThrowNoSuchElement(){
        Rating model = new Rating();
        when(repository.existsById(any())).thenReturn(false);
        service.putRating(model,1);
    }
    //add
    @Test
    @WithMockUser(value = "spring")
    public void addRating_shouldReturnOk(){
        Rating model = new Rating();
        model.setId(1);
        when(repository.existsById(any())).thenReturn(false);
        when(repository.save(any())).thenReturn(model);
        assertEquals(service.addRating(model),model);
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void addRating_shouldThrowNoSuchElement(){
        Rating model = new Rating();
        model.setId(1);
        when(repository.existsById(any())).thenReturn(true);
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.addRating(model);
    }
    //delete
    @Test
    @WithMockUser(value = "spring")
    public void deleteRating_shouldReturnOk(){
        Rating model = new Rating();
        model.setFitchRating("test");
        when(repository.findById(any())).thenReturn(Optional.of(model));
        doNothing().when(repository).delete(any());
        Rating modelResult = service.deleteRating(1);
        assertEquals(model.getFitchRating(), modelResult.getFitchRating());
    }
    @Test(expected = NoSuchElementException.class)
    @WithMockUser(value = "spring")
    public void deleteRating_shouldThrowNoSuchElement(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.deleteRating(1);
    }
}
