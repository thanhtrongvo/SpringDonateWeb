package com.example.springdonateweb.Repositories;

import com.example.springdonateweb.Models.Entities.DonorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorsRepository extends JpaRepository<DonorsEntity, Integer> {
}
