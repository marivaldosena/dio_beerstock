package com.github.marivaldosena.beerstock.beers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {
    private final BeerRepository beerRepository;

    @Autowired
    public BeerController(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeerDto createBeer(@RequestBody @Valid BeerRequest request) {
        Beer beer = request.toEntity();
        return new BeerDto(beer);
    }

    @GetMapping("/{name}")
    public BeerDto findByName(@PathVariable String name) {
        Optional<Beer> beer = beerRepository.findByName(name);
        return new BeerDto(beer.get());
    }

    @GetMapping
    public List<BeerDto> listBeers() {
        List<BeerDto> beers = beerRepository.findAll().stream()
                .map(BeerDto::new)
                .collect(Collectors.toList());
        return beers;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
    }

    @PatchMapping("/{id}/increment")
    public BeerDto increment(@PathVariable Long id, @RequestBody BeerRequest request) {
        Beer beer = request.toEntity();
        return new BeerDto(beer);
    }
}
