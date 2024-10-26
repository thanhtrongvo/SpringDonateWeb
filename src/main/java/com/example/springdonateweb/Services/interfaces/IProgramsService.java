package com.example.springdonateweb.Services.interfaces;

import com.example.springdonateweb.Models.Dtos.Programs.ProgramResponseDto;

import java.util.List;

public interface IProgramsService {
    List<ProgramResponseDto> findAll();
    ProgramResponseDto findByProgramId(int id);
}
