package com.kata.beerapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "breweries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brewery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la cervecera es obligatorio")
    @Column(nullable = false)
    private String name;

    @Column
    private String address1;

    @Column
    private String address2;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String code;

    @Column
    private String country;

    @Column
    private String phone;

    @Column
    private String website;

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
