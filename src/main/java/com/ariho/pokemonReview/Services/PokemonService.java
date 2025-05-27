package com.ariho.pokemonReview.Services;

import com.ariho.pokemonReview.DTO.PokemonDTO;

import java.util.List;

public interface PokemonService {
    PokemonDTO createPokemon(PokemonDTO pokemonDTO);
    List<PokemonDTO> getAllPokemon();
}
