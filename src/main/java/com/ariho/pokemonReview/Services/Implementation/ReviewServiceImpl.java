package com.ariho.pokemonReview.Services.Implementation;

import com.ariho.pokemonReview.DTO.PokemonDTO;
import com.ariho.pokemonReview.DTO.ReviewDTO;
import com.ariho.pokemonReview.Exceptions.PokemonNotFoundException;
import com.ariho.pokemonReview.Model.Pokemon;
import com.ariho.pokemonReview.Model.Review;
import com.ariho.pokemonReview.Repository.PokemonRepository;
import com.ariho.pokemonReview.Repository.ReviewRepository;
import com.ariho.pokemonReview.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final PokemonRepository pokemonRepository;
    private final ReviewRepository reviewRepository;
    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository){
        this.reviewRepository= reviewRepository;
        this.pokemonRepository = pokemonRepository;
    }


    @Override
    public ReviewDTO createReview(int pokemonId, ReviewDTO reviewDTO) {



        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(()-> new PokemonNotFoundException("Pokemon number "+pokemonId+" not found!"));
        Review review = new Review();

        review.setId(reviewDTO.getId());
        review.setTitle(reviewDTO.getTitle());
        System.out.println("reviewDTO.getTitle(): Going to the DB "+reviewDTO.getTitle());

        review.setContent(reviewDTO.getContent());
        review.setStars(reviewDTO.getStars());

        review.setPokemon(pokemon);
        Review updatedReview= reviewRepository.save(review);

        return mapToDTO(updatedReview);




    }

    @Override
    public List<ReviewDTO> getReviewByPokemonID(int pokemonId) {

        List<Review> reviewsList= reviewRepository.findByPokemonId(pokemonId);

        return reviewsList.stream().map(review -> mapToDTO(review)).collect(Collectors.toList());
    }

    private ReviewDTO mapToDTO(Review review){

        // Convert the Entity from the repository into a DTO since controllers interact with only DTOs
        ReviewDTO reviewDTO = new ReviewDTO();


        System.out.println(review.getTitle());
        reviewDTO.setId(review.getId());

        reviewDTO.setTitle(review.getTitle());
        reviewDTO.setContent(review.getContent());
        reviewDTO.setStars(review.getStars());
        return reviewDTO;
    }

    private Review mapToEntity(ReviewDTO reviewDTO){

        //Convert DTO that comes from the controller to a Review Object so that it can be stored in the
        //repository since the repository only interacts with POJOs/ Entities.

        Review review = new Review();

        review.setId(reviewDTO.getId());
        review.setTitle(reviewDTO.getTitle());
        review.setContent(reviewDTO.getContent());
        review.setStars(reviewDTO.getStars());

        return review;

    }
}
