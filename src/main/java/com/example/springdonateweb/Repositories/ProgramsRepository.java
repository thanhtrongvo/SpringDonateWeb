package com.example.springdonateweb.Repositories;

import com.example.springdonateweb.Models.Entities.ProgramsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramsRepository extends JpaRepository<ProgramsEntity, Integer> {
    List<ProgramsEntity> findAll();

    Optional<ProgramsEntity> findByProgramId(int id);

    @Query(value = "SELECT * FROM programs WHERE category_id = :id", nativeQuery = true)
    Optional<ProgramsEntity> findByCategoryId(int id);


}
