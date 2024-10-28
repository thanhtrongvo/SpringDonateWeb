package com.example.springdonateweb.Services.mappers;

import com.example.springdonateweb.Models.Dtos.Donors.DonorAddDto;
import com.example.springdonateweb.Models.Dtos.Donors.DonorCreateDto;
import com.example.springdonateweb.Models.Dtos.Donors.DonorDto;
import com.example.springdonateweb.Models.Dtos.Donors.DonorUpdateDto;
import com.example.springdonateweb.Models.Entities.DonorsEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DonorsMapper {
    DonorsEntity toEntity(DonorCreateDto donorCreateDto);
    DonorsEntity toEntity(DonorUpdateDto donorUpdateDto);
    DonorDto toDto(DonorsEntity donorsEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DonorsEntity partialUpdate(DonorUpdateDto donorUpdateDto, @MappingTarget DonorsEntity donorsEntity);
}
