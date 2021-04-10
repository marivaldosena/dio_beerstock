package com.github.marivaldosena.beerstock.beers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeerService {
    private final BeerRepository beerRepository;

    @Autowired
    public BeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public BeerDto createBeer(BeerRequest request) {
        Beer beer = request.toEntity();
        beerRepository.save(beer);
        return new BeerDto(beer);
    }

    public BeerDto findByName(String name) {
        Beer foundBeer = beerRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Beer not found"));
        return new BeerDto(foundBeer);
    }

    public List<BeerDto> listAll() {
        return beerRepository.findAll().stream()
                .map(BeerDto::new)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        beerRepository.deleteById(id);
    }


    public BeerDto increment(Long id, Integer quantityToIncrement) {
        Beer beer = beerRepository.findById(id).get();
        beer.updateQuantity(quantityToIncrement);
        beerRepository.save(beer);
        return new BeerDto(beer);
    }
}
