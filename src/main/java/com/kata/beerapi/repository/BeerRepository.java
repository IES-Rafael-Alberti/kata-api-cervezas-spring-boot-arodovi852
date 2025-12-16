package com.kata.beerapi.repository;

import com.kata.beerapi.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {
    List<Beer> findByNameContainingIgnoreCase(String name);
    List<Beer> findByBreweryId(Long breweryId);
    List<Beer> findByCatId(Long catId);
    List<Beer> findByStyleId(Long styleId);
}
