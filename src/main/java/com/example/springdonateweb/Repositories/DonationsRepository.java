package com.example.springdonateweb.Repositories;

import com.example.springdonateweb.Models.Entities.DonationsEntity;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationsRepository extends JpaRepository<DonationsEntity, Integer> {
    // Các phương thức tìm kiếm bổ sung nếu cần
    Page<DonationsEntity> findAll(Pageable pageable);

    List<DonationsEntity> findByUserId(int userId);

    @Query("SELECT d.donorName AS donorName, SUM(d.amount) AS totalAmount " +
            "FROM DonationsEntity d " +
            "GROUP BY d.donorName " +
            "ORDER BY totalAmount DESC")
    List<Map<String, Object>> findTopDonors(Pageable pageable);

    @Query("SELECT SUM(d.amount) FROM DonationsEntity d")
    java.math.BigDecimal getTotalDonations();

    @Query("SELECT COUNT(DISTINCT d.userId) FROM DonationsEntity d")
    long countDistinctDonors();

    @Query("SELECT COUNT(d.donationId) FROM DonationsEntity d")
    long countTotalDonations();

    @Query("SELECT SUM(d.amount) FROM DonationsEntity d WHERE MONTH(d.donationDate) = MONTH(CURRENT_DATE) AND YEAR(d.donationDate) = YEAR(CURRENT_DATE)")
    java.math.BigDecimal getMonthlyDonations();
}
