package com.kata.beerapi.repository;

import com.kata.beerapi.model.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreweryRepository extends JpaRepository<Brewery, Long> {
    List<Brewery> findByNameContainingIgnoreCase(String name);
    List<Brewery> findByCity(String city);
    List<Brewery> findByCountry(String country);
}
