package com.kata.beerapi.service;

import com.kata.beerapi.exception.ResourceNotFoundException;
import com.kata.beerapi.model.Beer;
import com.kata.beerapi.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BeerService {

    private final BeerRepository beerRepository;

    public List<Beer> getAllBeers() {
        return beerRepository.findAll();
    }

    public Beer getBeerById(Long id) {
        return beerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cerveza no encontrada con id: " + id));
    }

    public Beer createBeer(Beer beer) {
        return beerRepository.save(beer);
    }

    public Beer updateBeer(Long id, Beer beerDetails) {
        Beer beer = getBeerById(id);
        
        beer.setName(beerDetails.getName());
        beer.setBreweryId(beerDetails.getBreweryId());
        beer.setCatId(beerDetails.getCatId());
        beer.setStyleId(beerDetails.getStyleId());
        beer.setAbv(beerDetails.getAbv());
        beer.setIbu(beerDetails.getIbu());
        beer.setSrm(beerDetails.getSrm());
        beer.setUpc(beerDetails.getUpc());
        beer.setFilepath(beerDetails.getFilepath());
        beer.setDescript(beerDetails.getDescript());
        beer.setAddUser(beerDetails.getAddUser());
        
        return beerRepository.save(beer);
    }

    public Beer partialUpdateBeer(Long id, Beer beerDetails) {
        Beer beer = getBeerById(id);
        
        if (beerDetails.getName() != null) {
            beer.setName(beerDetails.getName());
        }
        if (beerDetails.getBreweryId() != null) {
            beer.setBreweryId(beerDetails.getBreweryId());
        }
        if (beerDetails.getCatId() != null) {
            beer.setCatId(beerDetails.getCatId());
        }
        if (beerDetails.getStyleId() != null) {
            beer.setStyleId(beerDetails.getStyleId());
        }
        if (beerDetails.getAbv() != null) {
            beer.setAbv(beerDetails.getAbv());
        }
        if (beerDetails.getIbu() != null) {
            beer.setIbu(beerDetails.getIbu());
        }
        if (beerDetails.getSrm() != null) {
            beer.setSrm(beerDetails.getSrm());
        }
        if (beerDetails.getUpc() != null) {
            beer.setUpc(beerDetails.getUpc());
        }
        if (beerDetails.getFilepath() != null) {
            beer.setFilepath(beerDetails.getFilepath());
        }
        if (beerDetails.getDescript() != null) {
            beer.setDescript(beerDetails.getDescript());
        }
        if (beerDetails.getAddUser() != null) {
            beer.setAddUser(beerDetails.getAddUser());
        }
        
        return beerRepository.save(beer);
    }

    public void deleteBeer(Long id) {
        Beer beer = getBeerById(id);
        beerRepository.delete(beer);
    }

    public List<Beer> searchBeersByName(String name) {
        return beerRepository.findByNameContainingIgnoreCase(name);
    }
}
