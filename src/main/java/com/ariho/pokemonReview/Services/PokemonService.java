package com.ariho.pokemonReview.Services;

import com.ariho.pokemonReview.DTO.PokemonResponse;
import com.ariho.pokemonReview.DTO.PokemonDTO;

public interface PokemonService {
    PokemonDTO createPokemon(PokemonDTO pokemonDTO);
    PokemonResponse getAllPokemon(int pageNo, int pageSize);
    PokemonDTO getPokemonByID(int id);
    PokemonDTO updatePokemon(PokemonDTO pokemonDTO, int id);
    void deletePokemon(int id);

}
