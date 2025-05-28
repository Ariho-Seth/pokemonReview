package com.ariho.pokemonReview.Services.Implementation;

import com.ariho.pokemonReview.DTO.PokemonResponse;
import com.ariho.pokemonReview.DTO.PokemonDTO;
import com.ariho.pokemonReview.Exceptions.PokemonNotFoundException;
import com.ariho.pokemonReview.Model.Pokemon;
import com.ariho.pokemonReview.Repository.PokemonRepository;
import com.ariho.pokemonReview.Services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public PokemonResponse getAllPokemon(int pageNo, int pageSize) {
        Pageable pagable= PageRequest.of(pageNo, pageSize);
        Page<Pokemon> pokemons= pokemonRepository.findAll(pagable);
        List<Pokemon> pokemonList = pokemons.getContent();

        List<PokemonDTO> content= pokemonList.stream().map(pokemon-> mapToDTO(pokemon)).collect(Collectors.toList());

        PokemonResponse pokemonResponse = new PokemonResponse();

        pokemonResponse.setContent(content);
        pokemonResponse.setPageNo(pokemons.getNumber());
        pokemonResponse.setPageSize(pokemons.getSize());
        pokemonResponse.setTotalElements(pokemonResponse.getTotalElements());
        pokemonResponse.setTotalPages(pokemons.getTotalPages());
        pokemonResponse.setLastPage(pokemons.isLast());

        return pokemonResponse;



    }

    @Override
    public PokemonDTO getPokemonByID(int id) {
        Pokemon pokemon= pokemonRepository.findById(id).orElseThrow(()-> new PokemonNotFoundException("Pokemon number "+id+" not found"));
        return mapToDTO(pokemon);
    }

    @Override
    public PokemonDTO updatePokemon(PokemonDTO pokemonDTO, int id) {
        Pokemon pokemon= pokemonRepository.findById(id).orElseThrow(()-> new PokemonNotFoundException("Pokemon number "+id+" not found"));

        pokemon.setName(pokemonDTO.getName());
        pokemon.setType(pokemonDTO.getType());
        Pokemon updatedPokemon= pokemonRepository.save(pokemon);
        return mapToDTO(updatedPokemon);
    }

    @Override
    public void deletePokemon(int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(()-> new PokemonNotFoundException("Pokemon number "+id+" not found"));
        pokemonRepository.delete(pokemon);

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
