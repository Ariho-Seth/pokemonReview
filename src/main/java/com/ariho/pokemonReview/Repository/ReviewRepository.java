package com.ariho.pokemonReview.Repository;

import com.ariho.pokemonReview.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByPokemonId(int id);

}