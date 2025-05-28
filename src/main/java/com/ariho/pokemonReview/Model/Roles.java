package com.ariho.pokemonReview.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@Table(name= "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable (name= "user_roles", joinColumns = @JoinColumn (name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns= @JoinColumn(name= "role_id", referencedColumnName = "id"))
    private List<Roles> rolesList =new ArrayList<>();
}
