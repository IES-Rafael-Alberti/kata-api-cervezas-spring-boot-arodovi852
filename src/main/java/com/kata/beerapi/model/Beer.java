package com.kata.beerapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "beers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brewery_id", nullable = false)
    private Long breweryId;

    @NotBlank(message = "El nombre de la cerveza es obligatorio")
    @Column(nullable = false)
    private String name;

    @Column(name = "cat_id", nullable = false)
    private Long catId;

    @Column(name = "style_id", nullable = false)
    private Long styleId;

    @Column(nullable = false)
    private Float abv; // Alcohol by volume

    @Column(nullable = false)
    private Float ibu; // International Bitterness Units

    @Column(nullable = false)
    private Float srm; // Standard Reference Method (color)

    @Column(nullable = false)
    private Integer upc;

    @Column
    private String filepath;

    @Column(columnDefinition = "TEXT")
    private String descript;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "last_mod")
    private LocalDateTime lastMod;

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        lastMod = LocalDateTime.now();
    }
}
