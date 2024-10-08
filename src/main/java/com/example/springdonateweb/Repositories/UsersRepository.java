package com.example.springdonateweb.Repositories;

import com.example.springdonateweb.Models.Entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<UsersEntity, Integer> {




    Optional<UsersEntity> findByEmail(String email);
}
