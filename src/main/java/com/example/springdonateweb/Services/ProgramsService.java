package com.example.springdonateweb.Services;

import com.example.springdonateweb.Models.Dtos.Programs.ProgramCreateDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramsResponseDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramUpdateDto;
import com.example.springdonateweb.Models.Entities.CategoriesEntity;
import com.example.springdonateweb.Models.Entities.ProgramsEntity;
import com.example.springdonateweb.Repositories.CategoriesRepository;
import com.example.springdonateweb.Repositories.ProgramsRepository;
import com.example.springdonateweb.Services.interfaces.IProgramsService;
import com.example.springdonateweb.Services.mappers.ProgramsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramsService implements IProgramsService {

    private final ProgramsRepository programsRepository;
    private final CategoriesRepository categoriesRepository;
    private final ProgramsMapper programsMapper;

    @Override
    public List<ProgramsResponseDto> findAll() {
        return programsRepository.findAll().stream()
                .map(programsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProgramsResponseDto findById(int id) {
        return programsRepository.findById(id)
                .map(programsMapper::toDto)
                .orElse(null);
    }

    @Override
    public ProgramsResponseDto create(ProgramCreateDto programCreateDto) {
        ProgramsEntity programsEntity = programsMapper.toEntity(programCreateDto);
        programsEntity.setCurrentAmount(0); // Đặt current_amount là 0
        programsEntity.setDonationCount(0); // Đặt donation_count là 0

        Optional<CategoriesEntity> category = categoriesRepository.findById(programCreateDto.getCategoryId());
        category.ifPresent(programsEntity::setCategory);

        ProgramsEntity savedProgram = programsRepository.save(programsEntity);
        return programsMapper.toDto(savedProgram);
    }


    @Override
    public ProgramsResponseDto update(ProgramUpdateDto programUpdateDto) {
        Optional<ProgramsEntity> program = programsRepository.findById(programUpdateDto.getProgramId());
        if (program.isPresent()) {
            ProgramsEntity updatedProgram = programsMapper.partialUpdate(programUpdateDto, program.get());
            Optional<CategoriesEntity> category = categoriesRepository.findById(programUpdateDto.getCategoryId());
            category.ifPresent(updatedProgram::setCategory);
            return programsMapper.toDto(programsRepository.save(updatedProgram));
        }
        return null;
    }
    public Page<ProgramsResponseDto> findProgramsByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProgramsEntity> programPage = programsRepository.findAll(pageable);
        return programPage.map(programsMapper::toDto);
    }

    @Override
    public void delete(int id) {
        programsRepository.deleteById(id);
    }

    @Override
    public List<ProgramsResponseDto> findByStatusTrue() {
        return programsRepository.findByStatusTrue().stream()
                .map(programsMapper::toDto)
                .collect(Collectors.toList());
    }
}
