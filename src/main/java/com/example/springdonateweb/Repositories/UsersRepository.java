package com.example.springdonateweb.Repositories;

import com.example.springdonateweb.Models.Entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {
        Optional<UsersEntity> findByEmail(String email);

        // Optional<UsersEntity> findByIdAndStatusTrue(Integer id);
        boolean existsByEmail(String email);

        List<UsersEntity> findByStatusTrue();

        Optional<UsersEntity> findByIdAndStatusTrue(int id);

        // boolean existsByIdAndStatusTrue(Integer id);
        Optional<UsersEntity> findByEmailAndStatusTrue(String email);

        @Query("SELECT u FROM UsersEntity u WHERE (:keyword IS NULL OR :keyword = '' OR " +
                        "LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                        "LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%')))")
        org.springframework.data.domain.Page<UsersEntity> searchUsers(@Param("keyword") String keyword,
                        org.springframework.data.domain.Pageable pageable);

        @Query(value = "SELECT DATE(created_at) AS date, COUNT(*) AS userCount " +
                        "FROM users " +
                        "WHERE created_at >= DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY) " +
                        "GROUP BY DATE(created_at) " +
                        "ORDER BY date ASC", nativeQuery = true)
        List<java.util.Map<String, Object>> getNewUsersByDay();

}
