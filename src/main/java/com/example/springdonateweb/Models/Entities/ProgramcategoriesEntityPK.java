package com.example.springdonateweb.Models.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class ProgramcategoriesEntityPK implements Serializable {
    @Column(name = "program_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int programId;
    @Column(name = "category_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramcategoriesEntityPK that = (ProgramcategoriesEntityPK) o;
        return programId == that.programId && categoryId == that.categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(programId, categoryId);
    }
}
