package com.ariho.pokemonReview.Exceptions;

public class PokemonNotFoundException extends RuntimeException{

    private static final long serialVersionUID= 1L;
    public PokemonNotFoundException(String message){

        super(message);
    }

}
