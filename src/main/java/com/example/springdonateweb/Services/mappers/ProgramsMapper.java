package com.example.springdonateweb.Services.mappers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramCreateDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramsResponseDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramUpdateDto;
import com.example.springdonateweb.Models.Entities.CategoriesEntity;
import com.example.springdonateweb.Models.Entities.ProgramsEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProgramsMapper {
    @Mappings({
        @Mapping(source = "image", target = "image", qualifiedByName = "multipartFileToString")
    })
    ProgramsEntity toEntity(ProgramCreateDto programsCreateDto);
    ProgramsEntity toEntity(ProgramUpdateDto programsUpdateDto);

     @Mappings({
            @Mapping(target = "percentageAchieved", expression = "java(calculatePercentage(entity.getCurrentAmount(), entity.getGoalAmount()))"),
            @Mapping(source = "category", target = "category", qualifiedByName = "categoryToString"),
            @Mapping(target = "startDate", expression = "java(convertDateToString(entity.getStartDate()))"),
            @Mapping(target = "endDate", expression = "java(convertDateToString(entity.getEndDate()))"),
            @Mapping(target = "remainingDays", expression = "java(calculateRemainingDays(convertDateToString(entity.getStartDate()), convertDateToString(entity.getEndDate())))")
    })
     ProgramsResponseDto toDto(ProgramsEntity entity);

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


    @Named("multipartFileToString")
    default String multipartFileToString(org.springframework.web.multipart.MultipartFile file) {
        if (file != null) {
            if (System.getenv("HEROKU_ENV") != null && System.getenv("HEROKU_ENV").equals("true")) {
                // Nếu đang chạy trên Heroku thì upload ảnh lên Cloudinary
                return uploadToCloudinary(file);
            } else {
                // Nếu đang chạy trên Local thì chỉ lấy tên file
                return file.getOriginalFilename();
            }
        }
        return null;
    }
    private String uploadToCloudinary(org.springframework.web.multipart.MultipartFile file) {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", System.getenv("CLOUDINARY_CLOUD_NAME"),
                "api_key", System.getenv("CLOUDINARY_API_KEY"),
                "api_secret", System.getenv("CLOUDINARY_API_SECRET")
        ));

        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return uploadResult.get("url").toString(); // Trả về URL của ảnh sau khi upload
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Trả về null nếu có lỗi
        }
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProgramsEntity partialUpdate(ProgramUpdateDto programsUpdateDto, @MappingTarget ProgramsEntity programsEntity);

    static boolean isHeroku() {
        return System.getenv("HEROKU_ENV") != null;
    }
}
