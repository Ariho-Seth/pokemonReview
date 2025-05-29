package com.ariho.pokemonReview.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name ="user")

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name= "user_roles", joinColumns = @JoinColumn(name ="user_id", referencedColumnName = "id"),
            inverseJoinColumns= @JoinColumn(name="role_id", referencedColumnName="id"))


    private List<Role> roles;
}
