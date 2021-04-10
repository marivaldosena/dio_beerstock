package com.github.marivaldosena.beerstock.beers;

import javax.persistence.*;

@Entity
@Table(name = "beers")
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private Integer max;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BeerType type;

    /**
     * @deprecated Hibernate only.
     */
    public Beer() {
    }

    public Beer(String name, String brand, Integer max, Integer quantity, BeerType type) {
        this.name = name;
        this.brand = brand;
        this.max = max;
        this.quantity = quantity;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getMax() {
        return max;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BeerType getType() {
        return type;
    }

    public void updateQuantity(Integer quantityToIncrement) {
        this.quantity += quantityToIncrement;
    }
}
