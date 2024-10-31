package com.example.springdonateweb.Repositories;

import com.example.springdonateweb.Models.Entities.DonationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationsRepository extends JpaRepository<DonationsEntity, Integer> {
}
