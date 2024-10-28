package com.example.springdonateweb.Repositories;

import com.example.springdonateweb.Models.Entities.PaymentmethodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodsRepository extends JpaRepository<PaymentmethodsEntity, Integer> {
}
