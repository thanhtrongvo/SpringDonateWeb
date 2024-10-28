package com.example.springdonateweb.Services;

import com.example.springdonateweb.Models.Dtos.Donors.DonorAddDto;
import com.example.springdonateweb.Models.Dtos.Donors.DonorCreateDto;
import com.example.springdonateweb.Models.Dtos.Donors.DonorDto;
import com.example.springdonateweb.Models.Dtos.Donors.DonorUpdateDto;
import com.example.springdonateweb.Models.Entities.DonorsEntity;
import com.example.springdonateweb.Repositories.DonorsRepository;
import com.example.springdonateweb.Services.interfaces.IDonorsService;
import com.example.springdonateweb.Services.mappers.DonorsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonorsService implements IDonorsService {

    private final DonorsRepository donorsRepository;
    private final DonorsMapper donorsMapper;

    @Override
    public List<DonorDto> findAll() {
        return donorsRepository.findAll().stream()
                .map(donorsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DonorDto findById(int id) {
        Optional<DonorsEntity> donor = donorsRepository.findById(id);
        return donor.map(donorsMapper::toDto).orElse(null);
    }

    @Override
    public DonorDto create(DonorCreateDto donorCreateDto) {
        DonorsEntity donor = donorsMapper.toEntity(donorCreateDto);
        DonorsEntity savedDonor = donorsRepository.save(donor);
        return donorsMapper.toDto(savedDonor);
    }

    @Override
    public DonorDto update(DonorUpdateDto donorUpdateDto) {
        Optional<DonorsEntity> donor = donorsRepository.findById(donorUpdateDto.getDonorId());
        return donor
                .map(d -> {
                    DonorsEntity updatedDonor = donorsMapper.partialUpdate(donorUpdateDto, d);
                    DonorsEntity result = donorsRepository.save(updatedDonor);
                    return donorsMapper.toDto(result);
                })
                .orElse(null);
    }

    @Override
    public void delete(int id) {
        donorsRepository.deleteById(id);
    }
}
