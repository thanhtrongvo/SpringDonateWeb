package com.example.springdonateweb.Models.Entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "donors", schema = "webmomo", catalog = "")
public class DonorsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "donor_id")
    private int donorId;
    @Basic
    @Column(name = "program_id")
    private Integer programId;
    @Basic
    @Column(name = "user_id")
    private Integer userId;
    @Basic
    @Column(name = "donation_id")
    private Integer donationId;
    @Basic
    @Column(name = "donation_date")
    private Timestamp donationDate;

    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDonationId() {
        return donationId;
    }

    public void setDonationId(Integer donationId) {
        this.donationId = donationId;
    }

    public Timestamp getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Timestamp donationDate) {
        this.donationDate = donationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DonorsEntity that = (DonorsEntity) o;
        return donorId == that.donorId && Objects.equals(programId, that.programId) && Objects.equals(userId, that.userId) && Objects.equals(donationId, that.donationId) && Objects.equals(donationDate, that.donationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(donorId, programId, userId, donationId, donationDate);
    }
}
