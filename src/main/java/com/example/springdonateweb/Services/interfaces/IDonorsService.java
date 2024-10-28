package com.example.springdonateweb.Services.interfaces;

import com.example.springdonateweb.Models.Dtos.Donors.DonorCreateDto;
import com.example.springdonateweb.Models.Dtos.Donors.DonorDto;
import com.example.springdonateweb.Models.Dtos.Donors.DonorUpdateDto;

import java.util.List;

public interface IDonorsService {
    List<DonorDto> findAll();
    DonorDto findById(int id);
    DonorDto create(DonorCreateDto donorCreateDto);
    DonorDto update(DonorUpdateDto donorUpdateDto);
    void delete(int id);
}
