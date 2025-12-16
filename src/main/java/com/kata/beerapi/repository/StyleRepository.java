package com.kata.beerapi.repository;

import com.kata.beerapi.model.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {
    List<Style> findByStyleNameContainingIgnoreCase(String styleName);
    List<Style> findByCatId(Long catId);
}
