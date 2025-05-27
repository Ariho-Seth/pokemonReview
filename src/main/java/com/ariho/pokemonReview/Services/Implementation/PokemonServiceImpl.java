package com.ariho.pokemonReview.Services.Implementation;

import com.ariho.pokemonReview.DTO.PokemonDTO;
import com.ariho.pokemonReview.Model.Pokemon;
import com.ariho.pokemonReview.Repository.PokemonRepository;
import com.ariho.pokemonReview.Services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {
    private PokemonRepository pokemonRepository;
    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository){
        this.pokemonRepository= pokemonRepository;
    }

    @Override
    public PokemonDTO createPokemon(PokemonDTO pokemonDTO) {
        Pokemon pokemon= new Pokemon();
        pokemon.setName(pokemonDTO.getName());
        pokemon.setType(pokemonDTO.getType());

        Pokemon newPokemon= pokemonRepository.save(pokemon);

        PokemonDTO pokemonResponse= new PokemonDTO();
        pokemonResponse.setId(newPokemon.getId());
        pokemonResponse.setName(newPokemon.getName());
        pokemonResponse.setType(newPokemon.getType());
        return pokemonResponse;
    }

    @Override
    public List<PokemonDTO> getAllPokemon() {
       List<Pokemon> pokemonList= pokemonRepository.findAll();
       return pokemonList.stream().map(pokemon-> mapToDTO(pokemon)).collect(Collectors.toList());

    }

    private PokemonDTO mapToDTO(Pokemon pokemon){
        PokemonDTO pokemonDTO= new PokemonDTO();
        pokemonDTO.setId(pokemon.getId());
        pokemonDTO.setName(pokemon.getName());
        pokemonDTO.setType(pokemon.getType());
        return pokemonDTO;
    }
    private Pokemon mapToEntity(PokemonDTO pokemonDTO){
        Pokemon pokemon= new Pokemon();
        pokemon.setId(pokemonDTO.getId());
        pokemon.setName(pokemonDTO.getName());
        pokemon.setType(pokemonDTO.getType());
        return pokemon;
    }
}
