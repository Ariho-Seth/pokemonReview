package com.ariho.pokemonReview.Services;

import com.ariho.pokemonReview.DTO.ReviewDTO;

import java.util.List;

public interface ReviewService {
   ReviewDTO createReview(int id, ReviewDTO reviewDTO);
   List<ReviewDTO> getReviewByPokemonID(int pokemonId);
   ReviewDTO getReviewById(int pokemonId, int reviewId);
   ReviewDTO updateReview(int pokemonId, int reviewId, ReviewDTO reviewDTO);
   void deleteReview(int pokemonId, int reviewId);
}
