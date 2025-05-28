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


}
