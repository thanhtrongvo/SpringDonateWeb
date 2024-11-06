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
                .map(programsMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProgramsResponseDto findById(int id) {
        return programsRepository.findById(id)
                .map(programsMapper::toResponseDto)
                .orElse(null);
    }


    @Override
    public ProgramsResponseDto create(ProgramCreateDto programCreateDto) {
        ProgramsEntity programsEntity = programsMapper.toEntity(programCreateDto);
        Optional<CategoriesEntity> category = categoriesRepository.findById(programCreateDto.getCategoryId());
        category.ifPresent(programsEntity::setCategory);
        ProgramsEntity savedProgram = programsRepository.save(programsEntity);
        return programsMapper.toResponseDto(savedProgram);
    }

    @Override
    public ProgramsResponseDto update(ProgramUpdateDto programUpdateDto) {
        Optional<ProgramsEntity> program = programsRepository.findById(programUpdateDto.getProgramId());
        if (program.isPresent()) {
            ProgramsEntity updatedProgram = programsMapper.partialUpdate(programUpdateDto, program.get());
            Optional<CategoriesEntity> category = categoriesRepository.findById(programUpdateDto.getCategoryId());
            category.ifPresent(updatedProgram::setCategory);
            return programsMapper.toResponseDto(programsRepository.save(updatedProgram));
        }
        return null;
    }

    @Override
    public void delete(int id) {
        // set status = false
        Optional<ProgramsEntity> program = programsRepository.findById(id);
        program.ifPresent(programsEntity -> {
            programsEntity.setStatus(false);
            programsRepository.save(programsEntity);
        });

    }

    @Override
    public List<ProgramsResponseDto> findByStatusTrue() {
        return programsRepository.findByStatusTrue().stream()
                .map(programsMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
