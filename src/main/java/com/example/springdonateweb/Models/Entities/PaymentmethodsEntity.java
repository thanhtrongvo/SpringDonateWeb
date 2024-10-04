package com.example.springdonateweb.Models.Entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "paymentmethods", schema = "webmomo", catalog = "")
public class PaymentmethodsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "payment_method_id")
    private int paymentMethodId;
    @Basic
    @Column(name = "method_name")
    private String methodName;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "is_active")
    private Byte isActive;

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentmethodsEntity that = (PaymentmethodsEntity) o;
        return paymentMethodId == that.paymentMethodId && Objects.equals(methodName, that.methodName) && Objects.equals(description, that.description) && Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentMethodId, methodName, description, isActive);
    }
}
