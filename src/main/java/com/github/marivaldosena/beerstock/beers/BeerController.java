package com.github.marivaldosena.beerstock.beers;

import com.github.marivaldosena.beerstock.errors.BeerAlreadyRegisteredException;
import com.github.marivaldosena.beerstock.errors.BeerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {
    private final BeerService beerService;

    @Autowired
    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeerDto createBeer(@RequestBody @Valid BeerRequest request) throws BeerAlreadyRegisteredException {
        return beerService.createBeer(request);
    }

    @GetMapping("/{name}")
    public BeerDto findByName(@PathVariable String name) throws BeerNotFoundException {
        return beerService.findByName(name);
    }

    @GetMapping
    public List<BeerDto> listBeers() {
        return beerService.listAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) throws BeerNotFoundException {
        beerService.deleteById(id);
    }

    @PatchMapping("/{id}/increment")
    public BeerDto increment(@PathVariable Long id, @RequestBody @Valid QuantityDto request) throws BeerNotFoundException {
        return beerService.increment(id, request.getQuantity());
    }
}
