package com.kata.beerapi.service;

import com.kata.beerapi.exception.ResourceNotFoundException;
import com.kata.beerapi.model.Style;
import com.kata.beerapi.repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StyleService {

    private final StyleRepository styleRepository;

    public List<Style> getAllStyles() {
        return styleRepository.findAll();
    }

    public Style getStyleById(Long id) {
        return styleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estilo no encontrado con id: " + id));
    }

    public List<Style> searchStylesByName(String name) {
        return styleRepository.findByStyleNameContainingIgnoreCase(name);
    }
}
