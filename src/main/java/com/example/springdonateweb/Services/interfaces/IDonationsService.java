package com.example.springdonateweb.Services.interfaces;

import com.example.springdonateweb.Models.Dtos.Donations.DonationCreateDto;
import com.example.springdonateweb.Models.Dtos.Donations.DonationResponseDto;
import com.example.springdonateweb.Models.Dtos.Donations.DonationUpdateDto;

import java.util.List;

public interface IDonationsService {
    List<DonationResponseDto> findAll();
    DonationResponseDto findById(int id);
    DonationResponseDto create(DonationCreateDto donationCreateDto);
    DonationResponseDto update(int id, DonationUpdateDto donationUpdateDto);
    void delete(int id);
}
