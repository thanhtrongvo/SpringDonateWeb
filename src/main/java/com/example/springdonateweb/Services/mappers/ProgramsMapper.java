package com.example.springdonateweb.Services.mappers;

import com.example.springdonateweb.Models.Dtos.Programs.ProgramCreateDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramsResponseDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramUpdateDto;
import com.example.springdonateweb.Models.Entities.CategoriesEntity;
import com.example.springdonateweb.Models.Entities.ProgramsEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.mapstruct.*;
import org.springframework.web.multipart.MultipartFile;

@Mapper(componentModel = "spring")
public interface ProgramsMapper {

    @Mappings({
            @Mapping(target = "image", expression = "java(handleImage(dto))")
    })
    ProgramsEntity toEntity(ProgramCreateDto dto);

    @Mappings({
            @Mapping(target = "image", expression = "java(handleUpdateImage(dto))")
    })
    ProgramsEntity toEntity(ProgramUpdateDto dto);

    @Mappings({
            @Mapping(target = "percentageAchieved", expression = "java(calculatePercentage(entity.getCurrentAmount(), entity.getGoalAmount()))"),
            @Mapping(source = "category", target = "category", qualifiedByName = "categoryToString"),
            @Mapping(target = "startDate", expression = "java(convertDateToString(entity.getStartDate()))"),
            @Mapping(target = "endDate", expression = "java(convertDateToString(entity.getEndDate()))"),
            @Mapping(target = "remainingDays", expression = "java(calculateRemainingDays(convertDateToString(entity.getStartDate()), convertDateToString(entity.getEndDate())))")
    })
    ProgramsResponseDto toDto(ProgramsEntity entity);

    // Custom method to handle image from either imageUrl or MultipartFile
    default String handleImage(ProgramCreateDto dto) {
        if (dto.getImageUrl() != null && !dto.getImageUrl().isEmpty()) {
            return dto.getImageUrl();
        } else if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            return dto.getImage().getOriginalFilename();
        }
        return null;
    }

    // Custom method to handle image updates
    default String handleUpdateImage(ProgramUpdateDto dto) {
        if (dto.getImageUrl() != null && !dto.getImageUrl().isEmpty()) {
            return dto.getImageUrl();
        } else if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            return dto.getImage().getOriginalFilename();
        }
        return null;
    }

    // Mapping method to extract the category name from CategoriesEntity
    @Named("categoryToString")
    default String mapCategoryToString(CategoriesEntity category) {
        return category != null ? category.getName() : null;
    }

    default int calculatePercentage(Integer currentAmount, Integer goalAmount) {
        if (goalAmount != null && goalAmount > 0 && currentAmount != null) {
            return (currentAmount * 100) / goalAmount;
        }
        return 0;
    }

    default long calculateRemainingDays(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        // Calculate the difference in days by converting to epoch days
        return end.toEpochDay() - start.toEpochDay();
    }

    default String convertDateToString(Date sqlDate) {
        return sqlDate != null ? sqlDate.toLocalDate().toString() : null;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProgramsEntity partialUpdate(ProgramUpdateDto programsUpdateDto, @MappingTarget ProgramsEntity programsEntity);
}