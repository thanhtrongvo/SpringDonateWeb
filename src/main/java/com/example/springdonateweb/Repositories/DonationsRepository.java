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

        @Query("SELECT d FROM DonationsEntity d WHERE (:keyword IS NULL OR :keyword = '' OR " +
                        "LOWER(d.donorName) LIKE LOWER(CONCAT('%', :keyword, '%')))")
        Page<DonationsEntity> searchDonations(
                        @org.springframework.data.repository.query.Param("keyword") String keyword,
                        Pageable pageable);

        List<DonationsEntity> findByUserId(int userId);

        @Query("SELECT d.donorName AS donorName, SUM(d.amount) AS totalAmount " +
                        "FROM DonationsEntity d " +
                        "GROUP BY d.donorName " +
                        "ORDER BY totalAmount DESC")
        List<Map<String, Object>> findTopDonors(Pageable pageable);

        @Query("SELECT pm.methodName AS paymentMethod, SUM(d.amount) AS totalAmount " +
                        "FROM DonationsEntity d, TransactionsEntity t, PaymentmethodsEntity pm " +
                        "WHERE d.donationId = t.donationId AND t.paymentMethodId = pm.paymentMethodId " +
                        "GROUP BY pm.methodName " +
                        "ORDER BY totalAmount DESC")
        List<Map<String, Object>> getTotalDonationsByPaymentMethod();

        @Query("SELECT SUM(d.amount) FROM DonationsEntity d")
        java.math.BigDecimal getTotalDonations();

        @Query("SELECT COUNT(DISTINCT d.userId) FROM DonationsEntity d")
        long countDistinctDonors();

        @Query("SELECT COUNT(d.donationId) FROM DonationsEntity d")
        long countTotalDonations();

        @Query("SELECT SUM(d.amount) FROM DonationsEntity d WHERE MONTH(d.donationDate) = MONTH(CURRENT_DATE) AND YEAR(d.donationDate) = YEAR(CURRENT_DATE)")
        java.math.BigDecimal getMonthlyDonations();

        @Query("SELECT p.name AS programName, SUM(d.amount) AS totalAmount " +
                        "FROM DonationsEntity d, ProgramsEntity p " +
                        "WHERE d.programId = p.programId " +
                        "GROUP BY p.name " +
                        "ORDER BY totalAmount DESC")
        List<Map<String, Object>> getTotalDonationsByProgram();

        @Query("SELECT p.category.name AS categoryName, SUM(d.amount) AS totalAmount " +
                        "FROM DonationsEntity d, ProgramsEntity p " +
                        "WHERE d.programId = p.programId " +
                        "GROUP BY p.category.name " +
                        "ORDER BY totalAmount DESC")
        List<Map<String, Object>> getTotalDonationsByCategory();
}
