package com.example.springdonateweb.Services.mappers;

import com.example.springdonateweb.Models.Dtos.Donations.DonationAddDto;
import com.example.springdonateweb.Models.Dtos.Donations.DonationCreateDto;
import com.example.springdonateweb.Models.Dtos.Donations.DonationDto;
import com.example.springdonateweb.Models.Dtos.Donations.DonationUpdateDto;
import com.example.springdonateweb.Models.Entities.DonationsEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DonationsMapper {
    DonationsEntity toEntity(DonationCreateDto donationCreateDto);
    DonationsEntity toEntity(DonationUpdateDto donationUpdateDto);
    DonationDto toDto(DonationsEntity donationsEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DonationsEntity partialUpdate(DonationUpdateDto donationUpdateDto, @MappingTarget DonationsEntity donationsEntity);
}
