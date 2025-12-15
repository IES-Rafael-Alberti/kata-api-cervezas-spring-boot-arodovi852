package com.kata.beerapi.controller;

import com.kata.beerapi.model.Beer;
import com.kata.beerapi.service.BeerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BeerController {

    private final BeerService beerService;

    // GET /beers - Obtener todas las cervezas
    @GetMapping("/beers")
    public ResponseEntity<List<Beer>> getAllBeers() {
        List<Beer> beers = beerService.getAllBeers();
        return ResponseEntity.ok(beers);
    }

    // GET /beer/{id} - Obtener una cerveza por ID
    @GetMapping("/beer/{id}")
    public ResponseEntity<Beer> getBeerById(@PathVariable Long id) {
        Beer beer = beerService.getBeerById(id);
        return ResponseEntity.ok(beer);
    }

    // POST /beer - Crear una nueva cerveza
    @PostMapping("/beer")
    public ResponseEntity<Beer> createBeer(@Valid @RequestBody Beer beer) {
        Beer createdBeer = beerService.createBeer(beer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBeer);
    }

    // PUT /beer/{id} - Actualizar completamente una cerveza
    @PutMapping("/beer/{id}")
    public ResponseEntity<Beer> updateBeer(@PathVariable Long id, @Valid @RequestBody Beer beer) {
        Beer updatedBeer = beerService.updateBeer(id, beer);
        return ResponseEntity.ok(updatedBeer);
    }

    // PATCH /beer/{id} - Actualizar parcialmente una cerveza
    @PatchMapping("/beer/{id}")
    public ResponseEntity<Beer> partialUpdateBeer(@PathVariable Long id, @RequestBody Beer beer) {
        Beer updatedBeer = beerService.partialUpdateBeer(id, beer);
        return ResponseEntity.ok(updatedBeer);
    }

    // DELETE /beer/{id} - Eliminar una cerveza
    @DeleteMapping("/beer/{id}")
    public ResponseEntity<Void> deleteBeer(@PathVariable Long id) {
        beerService.deleteBeer(id);
        return ResponseEntity.noContent().build();
    }
}
