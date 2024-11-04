package com.example.springdonateweb.Services.interfaces;

import com.example.springdonateweb.Models.Dtos.Programs.ProgramCreateDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramsResponseDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramUpdateDto;

import java.util.List;

public interface IProgramsService {
    List<ProgramsResponseDto> findAll();
    ProgramsResponseDto findById(int id);
    ProgramsResponseDto create(ProgramCreateDto programCreateDto);
    ProgramsResponseDto update(ProgramUpdateDto programUpdateDto);
    void delete(int id);
    List<ProgramsResponseDto> findByStatusTrue();
}
