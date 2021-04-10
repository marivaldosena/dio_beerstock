package com.github.marivaldosena.beerstock.beers;

import com.github.marivaldosena.beerstock.errors.BeerAlreadyRegisteredException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTests {
    @Mock
    private BeerRepository beerRepository;

    @InjectMocks
    private BeerService beerService;

    @ParameterizedTest
    @CsvSource({
            "Brahma,Ambev,10,50,LAGER",
            "Nome,Marca,10,15,MALZBIER",
            "Nome2,Marca2,10,10,WEISS"
    })
    void whenBeerIsInformedThenItShouldBeCreated(String name, String brand, Integer quantity, Integer max, BeerType type) {
        // Given
        BeerRequest request = new BeerRequestBuilder()
                .withBrand(brand)
                .withName(name)
                .withQuantity(quantity)
                .withMax(max)
                .withType(type)
                .build();
        Beer expectedBeer = request.toEntity();

        // When
        lenient().when(beerRepository.findByName(request.getName())).thenReturn(Optional.empty());
        lenient().when(beerRepository.save(expectedBeer)).thenReturn(expectedBeer);

        // Then
        BeerDto savedBeer = beerService.createBeer(request);

        assertThat(savedBeer.getName(), is(equalTo(expectedBeer.getName())));
        assertThat(savedBeer.getQuantity(), is(equalTo(expectedBeer.getQuantity())));
    }

    @ParameterizedTest
    @CsvSource({"Brahma,Ambev,10,50,LAGER"})
    void whenAlreadyRegisteredBeerInformedThenAnExceptionShouldBeThrown(String name, String brand, Integer quantity, Integer max, BeerType type) {
        BeerRequest request = new BeerRequestBuilder()
                .withBrand(brand)
                .withName(name)
                .withQuantity(quantity)
                .withMax(max)
                .withType(type)
                .build();
        Beer expectedBeer = request.toEntity();

        when(beerRepository.findByName(expectedBeer.getName())).thenReturn(Optional.of(expectedBeer));
        assertThrows(BeerAlreadyRegisteredException.class, () -> beerService.createBeer(request));
    }
}
