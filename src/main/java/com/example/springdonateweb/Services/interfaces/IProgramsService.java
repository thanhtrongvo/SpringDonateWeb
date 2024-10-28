package com.example.springdonateweb.Services.interfaces;

import com.example.springdonateweb.Models.Dtos.Programs.ProgramAddDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramDto;
import com.example.springdonateweb.Models.Dtos.Programs.ProgramUpdateDto;

import java.util.List;

public interface IProgramsService {
    List<ProgramDto> findAll();
    ProgramDto findById(int id);
    ProgramDto create(ProgramAddDto programAddDto);
    ProgramDto update(ProgramUpdateDto programUpdateDto);
    void delete(int id);
}
