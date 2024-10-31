package com.example.springdonateweb.Services.mappers;

import com.example.springdonateweb.Models.Dtos.Programs.ProgramCreateDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramsResponseDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramUpdateDto;
import com.example.springdonateweb.Models.Entities.ProgramsEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProgramsMapper {
    ProgramsEntity toEntity(ProgramCreateDto programsCreateDto);
    ProgramsEntity toEntity(ProgramUpdateDto programsUpdateDto);
    ProgramsResponseDto toDto(ProgramsEntity programsEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProgramsEntity partialUpdate(ProgramUpdateDto programsUpdateDto, @MappingTarget ProgramsEntity programsEntity);
}
