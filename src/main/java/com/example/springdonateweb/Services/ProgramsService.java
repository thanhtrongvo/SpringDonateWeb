package com.example.springdonateweb.Services;

import com.example.springdonateweb.Models.Dtos.Programs.ProgramAddDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramUpdateDto;
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

    private final ProgramsRepository programsRepository;
    private final ProgramsMapper programsMapper;

    @Override
    public List<ProgramDto> findAll() {
        return programsRepository.findAll().stream()
                .map(programsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProgramDto findById(int id) {
        Optional<ProgramsEntity> program = programsRepository.findById(id);
        return program.map(programsMapper::toDto).orElse(null);
    }

    @Override
    public ProgramDto create(ProgramAddDto programAddDto) {
        ProgramsEntity programsEntity = programsMapper.toEntity(programAddDto);
        ProgramsEntity savedProgram = programsRepository.save(programsEntity);
        return programsMapper.toDto(savedProgram);
    }

    @Override
    public ProgramDto update(ProgramUpdateDto programUpdateDto) {
        Optional<ProgramsEntity> program = programsRepository.findById(programUpdateDto.getProgramId());
        return program
                .map(prog -> {
                    ProgramsEntity updatedProgram = programsMapper.partialUpdate(programUpdateDto, prog);
                    ProgramsEntity result = programsRepository.save(updatedProgram);
                    return programsMapper.toDto(result);
                })
                .orElse(null);
    }

    @Override
    public void delete(int id) {
        programsRepository.deleteById(id);
    }
}
