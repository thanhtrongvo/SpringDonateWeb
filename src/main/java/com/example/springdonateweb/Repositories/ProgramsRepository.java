package com.example.springdonateweb.Repositories;

import com.example.springdonateweb.Models.Entities.ProgramsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramsRepository extends JpaRepository<ProgramsEntity, Integer> {
    List<ProgramsEntity> findByStatusTrue(); // Lấy danh sách các chương trình đang hoạt động
}
