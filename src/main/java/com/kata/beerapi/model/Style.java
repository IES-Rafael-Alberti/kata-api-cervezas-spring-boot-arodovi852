package com.kata.beerapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "styles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cat_id", nullable = false)
    private Long catId;

    @NotBlank(message = "El nombre del estilo es obligatorio")
    @Column(name = "style_name", nullable = false)
    private String styleName;

    @Column(name = "last_mod")
    private LocalDateTime lastMod;

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        lastMod = LocalDateTime.now();
    }
}
