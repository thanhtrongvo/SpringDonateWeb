package com.example.springdonateweb.Services.mappers;

import com.example.springdonateweb.Models.Dtos.Programs.ProgramAddDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramUpdateDto;
import com.example.springdonateweb.Models.Entities.ProgramsEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProgramsMapper {
    ProgramsEntity toEntity(ProgramAddDto programAddDto);
    ProgramsEntity toEntity(ProgramUpdateDto programUpdateDto);
    ProgramDto toDto(ProgramsEntity programsEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProgramsEntity partialUpdate(ProgramUpdateDto programUpdateDto, @MappingTarget ProgramsEntity programsEntity);
}
