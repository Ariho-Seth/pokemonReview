package com.ariho.pokemonReview.Controllers;

import com.ariho.pokemonReview.DTO.PokemonDTO;
import com.ariho.pokemonReview.Model.Pokemon;
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
    public ResponseEntity<List<PokemonDTO>> getPokemons(){

        return new ResponseEntity<>(pokemonService.getAllPokemon(), HttpStatus.OK);
    }
    @GetMapping("pokemon/{id}")
    public Pokemon pokemonDetail(@PathVariable int id){
        return new Pokemon(id, "Squirrel", "Broiler");
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDTO> createPokemon(@RequestBody PokemonDTO pokemonDTO){
       return new ResponseEntity<>(pokemonService.createPokemon(pokemonDTO), HttpStatus.CREATED);

    }
    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<Pokemon> updatePokemon(@RequestBody Pokemon pokemon, @PathVariable("id") int pokenomId){
        System.out.println(pokemon.getName());
        System.out.println(pokemon.getType());
        return ResponseEntity.ok(pokemon);
    }
    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") int pokenomId ){
        System.out.println(pokenomId);
        return ResponseEntity.ok("Pokemon "+pokenomId+" deleted successfully!");
    }
}
