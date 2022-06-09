package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RatingService {
    @Autowired
    private RatingRepository repository;

    public List<Rating> getAllRating(){
        return repository.findAll();
    }

    public Rating getRating(final Integer id){
        return repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with getRating"+id));
    }

    public Rating addRating(Rating domain){
        Integer id = domain.getId();
        if(id == null || !repository.existsById(id)){
            return repository.save(domain);
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with addRating"+id));
        }
    }

    public Rating putRating(Rating currentDomain, final Integer id){
        if (repository.existsById(id)){
            Rating domain = currentDomain;
            currentDomain = repository.findById(id).get();
            String moodysRating = domain.getMoodysRating();
            if (moodysRating != null) {
                currentDomain.setMoodysRating(moodysRating);
            }
            String sandPRating = domain.getSandPRating();
            if (sandPRating != null) {
                currentDomain.setSandPRating(sandPRating);
            }
            String fitchRating = domain.getFitchRating();
            if(fitchRating!=null){
                currentDomain.setFitchRating(fitchRating);
            }
            Integer orderNumber = domain.getOrderNumber();
            if(orderNumber!=null){
                currentDomain.setOrderNumber(orderNumber);
            }
            return repository.save(currentDomain);
        } else {
            return repository.findById(id).orElseThrow(()->
                    new NoSuchElementException("Error with putRating "+id));
        }
    }

    public Rating deleteRating(final Integer id){
        Rating domain = repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with deleteRating "+id));
        Rating copy = Rating.builder()
                .moodysRating(domain.getMoodysRating())
                .sandPRating(domain.getSandPRating())
                .fitchRating(domain.getFitchRating())
                .orderNumber(domain.getOrderNumber())
                .build();
        repository.delete(domain);
        return copy;
    }
}
