package com.example.springdonateweb.Repositories;

import com.example.springdonateweb.Models.Entities.PaymentmethodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentmethodsEntity, Integer> {
}