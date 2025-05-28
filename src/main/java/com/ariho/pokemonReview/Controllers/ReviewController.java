package com.ariho.pokemonReview.Controllers;

import com.ariho.pokemonReview.DTO.ReviewDTO;
import com.ariho.pokemonReview.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService= reviewService;
    }

    @PostMapping("pokemon/{pokemonId}/review")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReviewDTO> createReview(@PathVariable(value="pokemonId") int pokemonId, @RequestBody ReviewDTO reviewDTO){
        return new ResponseEntity<>(reviewService.createReview(pokemonId, reviewDTO), HttpStatus.CREATED);
    }

    @GetMapping("pokemon/{pokemonId}/reviews")
    public List<ReviewDTO> getReview(@PathVariable(value= "pokemonId") int id){
        return reviewService.getReviewByPokemonID(id);
    }
    @GetMapping("pokemon/{pokemonId}/review/{reviewId}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable(value= "pokemonId") int pokemonId, @PathVariable(value = "reviewId") int reviewId){
        return new ResponseEntity<> (reviewService.getReviewById(pokemonId, reviewId), HttpStatus.OK);
    }

    @PutMapping("pokemon/{pokemonId}/review/{reviewId}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable(value="pokemonId") int pokemonId, @PathVariable(value= "reviewId") int reviewId, @RequestBody ReviewDTO reviewDTO){
        return new ResponseEntity<>(reviewService.updateReview(pokemonId, reviewId, reviewDTO), HttpStatus.OK);
    }
    @DeleteMapping("pokemon/{pokemonId}/review/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable(value="pokemonId") int pokemonId, @PathVariable(value = "reviewId") int reviewId){
        reviewService.deleteReview(pokemonId, reviewId);
        return new ResponseEntity<>("Review Deleted!", HttpStatus.OK);
    }

}
