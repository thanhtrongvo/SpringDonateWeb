package com.example.springdonateweb.Repositories;

import com.example.springdonateweb.Models.Entities.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Integer> {
    CategoriesEntity findByName(String name);
    boolean existsByName(String name);
    Optional<CategoriesEntity> findById(int id);
    boolean existsById(int id);

}
