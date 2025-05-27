package com.ariho.pokemonReview.Model;

import lombok.Data;

@Data
public class Review {
    private int id;
    private String name;
    private String type;
    private int stars;

}
