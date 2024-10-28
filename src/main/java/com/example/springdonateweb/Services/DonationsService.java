package com.example.springdonateweb.Services;

import com.example.springdonateweb.Models.Dtos.Donations.DonationAddDto;
import com.example.springdonateweb.Models.Dtos.Donations.DonationCreateDto;
import com.example.springdonateweb.Models.Dtos.Donations.DonationDto;
import com.example.springdonateweb.Models.Dtos.Donations.DonationUpdateDto;
import com.example.springdonateweb.Models.Entities.DonationsEntity;
import com.example.springdonateweb.Repositories.DonationsRepository;
import com.example.springdonateweb.Services.interfaces.IDonationsService;
import com.example.springdonateweb.Services.mappers.DonationsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonationsService implements IDonationsService {

    private final DonationsRepository donationsRepository;
    private final DonationsMapper donationsMapper;

    @Override
    public List<DonationDto> findAll() {
        return donationsRepository.findAll().stream()
                .map(donationsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DonationDto findById(int id) {
        Optional<DonationsEntity> donation = donationsRepository.findById(id);
        return donation.map(donationsMapper::toDto).orElse(null);
    }

    @Override
    public DonationDto create(DonationCreateDto donationCreateDto) {
        DonationsEntity donation = donationsMapper.toEntity(donationCreateDto);
        DonationsEntity savedDonation = donationsRepository.save(donation);
        return donationsMapper.toDto(savedDonation);
    }

    @Override
    public DonationDto update(DonationUpdateDto donationUpdateDto) {
        Optional<DonationsEntity> donation = donationsRepository.findById(donationUpdateDto.getDonationId());
        return donation
                .map(d -> {
                    DonationsEntity updatedDonation = donationsMapper.partialUpdate(donationUpdateDto, d);
                    DonationsEntity result = donationsRepository.save(updatedDonation);
                    return donationsMapper.toDto(result);
                })
                .orElse(null);
    }

    @Override
    public void delete(int id) {
        donationsRepository.deleteById(id);
    }
}
