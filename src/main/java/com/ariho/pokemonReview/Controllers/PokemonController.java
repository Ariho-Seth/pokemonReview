package com.ariho.pokemonReview.Controllers;

import com.ariho.pokemonReview.DTO.PokemonDTO;

import com.ariho.pokemonReview.DTO.PokemonResponse;
import com.ariho.pokemonReview.Services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PokemonController {

    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService){
        this.pokemonService= pokemonService;
    }

    @GetMapping("pokemon")
    public ResponseEntity<PokemonResponse> getPokemons(
            @RequestParam(value = "pageNo", defaultValue="0", required= false ) int pageNo,
            @RequestParam(value= "pageSize", defaultValue = "10", required= false ) int pageSize
            ){

        return new ResponseEntity<>(pokemonService.getAllPokemon(pageNo, pageSize), HttpStatus.OK);
    }
    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDTO> pokemonDetail(@PathVariable int id){
         return ResponseEntity.ok(pokemonService.getPokemonByID(id));
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDTO> createPokemon(@RequestBody PokemonDTO pokemonDTO){
       return new ResponseEntity<>(pokemonService.createPokemon(pokemonDTO), HttpStatus.CREATED);

    }
    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<PokemonDTO> updatePokemon(@RequestBody PokemonDTO pokemonDTO, @PathVariable("id") int pokenomId){
        PokemonDTO updatedpokemonDTO = pokemonService.updatePokemon(pokemonDTO, pokenomId);
       return new ResponseEntity<>(updatedpokemonDTO, HttpStatus.OK);
    }
    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") int pokemonId ){
       pokemonService.deletePokemon(pokemonId);
       return new ResponseEntity<>("Pokemon "+pokemonId+" deleted", HttpStatus.OK);
    }
}
