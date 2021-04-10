package com.github.marivaldosena.beerstock.documentation;

import com.github.marivaldosena.beerstock.beers.BeerDto;
import com.github.marivaldosena.beerstock.beers.BeerRequest;
import com.github.marivaldosena.beerstock.errors.BeerAlreadyRegisteredException;
import com.github.marivaldosena.beerstock.errors.BeerNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api("Manages beer stock")
public interface BeerControllerDocs {
    @ApiOperation(value = "Beer creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success beer creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    BeerDto createBeer(BeerRequest request) throws BeerAlreadyRegisteredException;

    @ApiOperation(value = "Returns beer found by a given name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Beer found successfully"),
            @ApiResponse(code = 404, message = "Beer not found")
    })
    BeerDto findByName(@PathVariable String name) throws BeerNotFoundException;

    @ApiOperation(value = "Returns a lista of all beers registered")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all registered beers")
    })
    List<BeerDto> listBeers();

    @ApiOperation(value = "Delete a beer found by a valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Beer deleted sucessfully"),
            @ApiResponse(code = 404, message = "Beer not found")
    })
    void deleteById(@PathVariable Long id) throws BeerNotFoundException;
}
