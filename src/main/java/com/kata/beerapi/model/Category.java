package com.kata.beerapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    @Column(name = "cat_name", nullable = false)
    private String catName;

    @Column(name = "last_mod")
    private LocalDateTime lastMod;

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        lastMod = LocalDateTime.now();
    }
}
