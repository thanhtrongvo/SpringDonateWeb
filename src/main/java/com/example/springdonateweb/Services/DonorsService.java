package com.example.springdonateweb.Services;

import com.example.springdonateweb.Models.Dtos.Donors.DonorCreateDto;
import com.example.springdonateweb.Models.Dtos.Donors.DonorResponseDto;
import com.example.springdonateweb.Models.Dtos.Donors.DonorUpdateDto;
import com.example.springdonateweb.Models.Entities.DonorsEntity;
import com.example.springdonateweb.Repositories.DonorsRepository;
import com.example.springdonateweb.Services.interfaces.IDonorsService;
import com.example.springdonateweb.Services.mappers.DonorsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonorsService implements IDonorsService {

    private final DonorsRepository donorsRepository;
    private final DonorsMapper donorsMapper;
    private final com.example.springdonateweb.Repositories.ProgramsRepository programsRepository;
    private final com.example.springdonateweb.Repositories.UsersRepository usersRepository;

    private DonorResponseDto enhanceDto(DonorResponseDto dto) {
        if (dto == null)
            return null;
        if (dto.getProgramId() != null) {
            programsRepository.findById(dto.getProgramId()).ifPresent(p -> dto.setProgramName(p.getName()));
        }
        if (dto.getUserId() != null) {
            usersRepository.findById(dto.getUserId()).ifPresent(u -> dto.setUserName(u.getName()));
        }
        return dto;
    }

    @Override
    public List<DonorResponseDto> findAll() {
        return donorsRepository.findAll().stream()
                .map(donorsMapper::toDto)
                .map(this::enhanceDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<DonorResponseDto> findDonorsByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DonorsEntity> donorsPage = donorsRepository.findAll(pageable);
        return donorsPage.map(donorsMapper::toDto).map(this::enhanceDto);
    }

    @Override
    public DonorResponseDto findById(int id) {
        return donorsRepository.findById(id)
                .map(donorsMapper::toDto)
                .orElse(null);
    }

    @Override
    public DonorResponseDto create(DonorCreateDto donorCreateDto) {
        DonorsEntity donorsEntity = donorsMapper.toEntity(donorCreateDto);
        DonorsEntity savedDonor = donorsRepository.save(donorsEntity);
        return donorsMapper.toDto(savedDonor);
    }

    @Override
    public DonorResponseDto update(int id, DonorUpdateDto donorUpdateDto) {
        return donorsRepository.findById(id)
                .map(existingDonor -> {
                    DonorsEntity updatedDonor = donorsMapper.partialUpdate(donorUpdateDto, existingDonor);
                    return donorsMapper.toDto(donorsRepository.save(updatedDonor));
                })
                .orElse(null);
    }

    @Override
    public void delete(int id) {
        donorsRepository.deleteById(id);
    }
}
