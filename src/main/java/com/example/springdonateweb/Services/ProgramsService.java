package com.example.springdonateweb.Services;

import com.example.springdonateweb.Models.Dtos.Programs.ProgramResponseDto;
import com.example.springdonateweb.Models.Entities.ProgramsEntity;
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

    private final ProgramsRepository programReposotory;
    private final ProgramsMapper programsMapper;

    @Override
    public List<ProgramResponseDto> findAll() {
        return programReposotory.findAll()
                .stream()
                .map(programsMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProgramResponseDto findByProgramId(int id) {
        Optional<ProgramsEntity> program = programReposotory.findByProgramId(id);
        return program.map(programsMapper::toResponseDto).orElse(null);
    }

    @Override
    public ProgramResponseDto findByCategoryId(int id) {
        Optional<ProgramsEntity> program = programReposotory.findByCategoryId(id);
        return program.map(programsMapper::toResponseDto).orElse(null);
    }
}
