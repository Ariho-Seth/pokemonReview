package com.ariho.pokemonReview.Repository;

import com.ariho.pokemonReview.Model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

}
