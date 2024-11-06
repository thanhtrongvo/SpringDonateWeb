package com.example.springdonateweb.Services;

import com.example.springdonateweb.Models.Dtos.Donations.DonationCreateDto;
import com.example.springdonateweb.Models.Dtos.Donations.DonationResponseDto;
import com.example.springdonateweb.Models.Dtos.Donations.DonationUpdateDto;
import com.example.springdonateweb.Models.Entities.DonationsEntity;
import com.example.springdonateweb.Repositories.DonationsRepository;
import com.example.springdonateweb.Services.interfaces.IDonationsService;
import com.example.springdonateweb.Services.mappers.DonationsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonationsService implements IDonationsService {

    private final DonationsRepository donationsRepository;
    private final DonationsMapper donationsMapper;

    @Override
    public List<DonationResponseDto> findAll() {
        return donationsRepository.findAll().stream()
                .map(donationsMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public Page<DonationResponseDto> findDonationsByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DonationsEntity> donationPage = donationsRepository.findAll(pageable);
        return donationPage.map(donationsMapper::toDto);
    }
    @Override
    public DonationResponseDto findById(int id) {
        return donationsRepository.findById(id)
                .map(donationsMapper::toDto)
                .orElse(null);
    }

    @Override
    public DonationResponseDto create(DonationCreateDto donationCreateDto) {
        DonationsEntity donationsEntity = donationsMapper.toEntity(donationCreateDto);
        DonationsEntity savedDonation = donationsRepository.save(donationsEntity);
        return donationsMapper.toDto(savedDonation);
    }

    @Override
    public DonationResponseDto update(int id, DonationUpdateDto donationUpdateDto) {
        return donationsRepository.findById(id)
                .map(existingDonation -> {
                    DonationsEntity updatedDonation = donationsMapper.partialUpdate(donationUpdateDto, existingDonation);
                    return donationsMapper.toDto(donationsRepository.save(updatedDonation));
                })
                .orElse(null);
    }

    @Override
    public void delete(int id) {
        donationsRepository.deleteById(id);
    }
}
