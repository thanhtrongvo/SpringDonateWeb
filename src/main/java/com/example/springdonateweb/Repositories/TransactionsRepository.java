package com.example.springdonateweb.Repositories;

import com.example.springdonateweb.Models.Entities.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<TransactionsEntity, Integer> {
}
