package com.kata.beerapi.service;

import com.kata.beerapi.exception.ResourceNotFoundException;
import com.kata.beerapi.model.Brewery;
import com.kata.beerapi.repository.BreweryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BreweryService {

    private final BreweryRepository breweryRepository;

    public List<Brewery> getAllBreweries() {
        return breweryRepository.findAll();
    }

    public Brewery getBreweryById(Long id) {
        return breweryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cervecera no encontrada con id: " + id));
    }

    public List<Brewery> searchBreweriesByName(String name) {
        return breweryRepository.findByNameContainingIgnoreCase(name);
    }
}
