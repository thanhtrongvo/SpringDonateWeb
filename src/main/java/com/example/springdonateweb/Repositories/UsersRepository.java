package com.example.springdonateweb.Repositories;

import com.example.springdonateweb.Models.Entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {

    public UsersEntity findByEmail(String email);

}
