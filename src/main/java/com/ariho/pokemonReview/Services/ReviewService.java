package com.ariho.pokemonReview.Services;

import com.ariho.pokemonReview.DTO.ReviewDTO;

import java.util.List;

public interface ReviewService {
   ReviewDTO createReview(int id, ReviewDTO reviewDTO);
   List<ReviewDTO> getReviewByPokemonID(int pokemonId);

}
