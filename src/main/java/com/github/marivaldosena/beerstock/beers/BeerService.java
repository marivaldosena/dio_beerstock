package com.github.marivaldosena.beerstock.beers;

import com.github.marivaldosena.beerstock.errors.BeerAlreadyRegisteredException;
import com.github.marivaldosena.beerstock.errors.BeerNotFoundException;
import com.github.marivaldosena.beerstock.errors.BeerStockExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BeerService {
    private final BeerRepository beerRepository;

    @Autowired
    public BeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public BeerDto createBeer(BeerRequest request) throws BeerAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(request.getName());
        Beer beer = request.toEntity();
        beerRepository.save(beer);
        return new BeerDto(beer);
    }

    public BeerDto findByName(String name) throws BeerNotFoundException {
        Beer foundBeer = beerRepository.findByName(name)
                .orElseThrow(() -> new BeerNotFoundException(name));
        return new BeerDto(foundBeer);
    }

    public List<BeerDto> listAll() {
        return beerRepository.findAll().stream()
                .map(BeerDto::new)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) throws BeerNotFoundException {
        verifyIfExists(id);
        beerRepository.deleteById(id);
    }

    public BeerDto increment(Long id, Integer quantityToIncrement) throws BeerNotFoundException, BeerStockExceededException {
        Beer beer = verifyIfExists(id);
        beer.updateQuantity(quantityToIncrement);
        beerRepository.save(beer);
        return new BeerDto(beer);
    }

    private void verifyIfIsAlreadyRegistered(String name) throws BeerAlreadyRegisteredException {
        Optional<Beer> existingBeer = beerRepository.findByName(name);

        if (existingBeer.isPresent()) {
            throw new BeerAlreadyRegisteredException(name);
        }
    }

    private Beer verifyIfExists(Long id) throws BeerNotFoundException {
        return beerRepository.findById(id)
                .orElseThrow(() -> new BeerNotFoundException(id));
    }
}
