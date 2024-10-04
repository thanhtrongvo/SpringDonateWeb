package com.example.springdonateweb.Models.Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "donations", schema = "webmomo", catalog = "")
public class DonationsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "donation_id")
    private int donationId;
    @Basic
    @Column(name = "user_id")
    private Integer userId;
    @Basic
    @Column(name = "program_id")
    private Integer programId;
    @Basic
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic
    @Column(name = "donation_date")
    private Timestamp donationDate;
    @Basic
    @Column(name = "donor_name")
    private String donorName;

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Timestamp donationDate) {
        this.donationDate = donationDate;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DonationsEntity that = (DonationsEntity) o;
        return donationId == that.donationId && Objects.equals(userId, that.userId) && Objects.equals(programId, that.programId) && Objects.equals(amount, that.amount) && Objects.equals(donationDate, that.donationDate) && Objects.equals(donorName, that.donorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(donationId, userId, programId, amount, donationDate, donorName);
    }
}
