package com.example.springdonateweb.Repositories;

import com.example.springdonateweb.Models.Entities.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<CommentsEntity, Integer> {
}
