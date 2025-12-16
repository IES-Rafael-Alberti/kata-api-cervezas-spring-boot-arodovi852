package com.kata.beerapi.controller;

import com.kata.beerapi.model.Brewery;
import com.kata.beerapi.service.BreweryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BreweryController {

    private final BreweryService breweryService;

    // GET /breweries - Obtener todas las cerveceras
    @GetMapping("/breweries")
    public ResponseEntity<List<Brewery>> getAllBreweries() {
        List<Brewery> breweries = breweryService.getAllBreweries();
        return ResponseEntity.ok(breweries);
    }

    // GET /brewerie/{id} - Obtener una cervecera por ID
    @GetMapping("/brewerie/{id}")
    public ResponseEntity<Brewery> getBreweryById(@PathVariable Long id) {
        Brewery brewery = breweryService.getBreweryById(id);
        return ResponseEntity.ok(brewery);
    }
}
