package com.example.springdonateweb.Repositories;

import com.example.springdonateweb.Models.Entities.PaymentmethodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaymentRepository extends JpaRepository<PaymentmethodsEntity, Integer> {
    PaymentmethodsEntity findByIsActive(Byte isActive);
    PaymentmethodsEntity findByMethodName(String methodName);
    List<PaymentmethodsEntity> findAll();

}
