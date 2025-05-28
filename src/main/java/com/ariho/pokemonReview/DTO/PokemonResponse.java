package com.ariho.pokemonReview.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PokemonResponse {
    private List<PokemonDTO> content;
    private int pageNo;
    private int pageSize;
    private int totalElements;
    private int totalPages;
    private boolean lastPage;
}
