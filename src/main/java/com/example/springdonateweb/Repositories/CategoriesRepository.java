package com.example.springdonateweb.Repositories;

import com.example.springdonateweb.Models.Entities.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Integer> {
    List<CategoriesEntity> findAllByCategoryId(int id);
    List<CategoriesEntity> findAll();


}
