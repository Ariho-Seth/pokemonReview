package com.ariho.pokemonReview.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name= "roles")
public class Role {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String username;

}
