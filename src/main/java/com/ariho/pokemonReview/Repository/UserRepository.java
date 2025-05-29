package com.ariho.pokemonReview.Repository;

import com.ariho.pokemonReview.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String name);
    Boolean existsByUsername(String username);
}
