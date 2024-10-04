package com.example.springdonateweb.Models.Entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "programcategories", schema = "webmomo", catalog = "")
@IdClass(ProgramcategoriesEntityPK.class)
public class ProgramcategoriesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "program_id")
    private int programId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "category_id")
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
        ProgramcategoriesEntity that = (ProgramcategoriesEntity) o;
        return programId == that.programId && categoryId == that.categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(programId, categoryId);
    }
}
