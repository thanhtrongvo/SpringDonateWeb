package com.example.springdonateweb.Services.interfaces;

import com.example.springdonateweb.Models.Dtos.Donations.DonationCreateDto;
import com.example.springdonateweb.Models.Dtos.Donations.DonationDto;
import com.example.springdonateweb.Models.Dtos.Donations.DonationUpdateDto;

import java.util.List;

public interface IDonationsService {
    List<DonationDto> findAll();
    DonationDto findById(int id);
    DonationDto create(DonationCreateDto donationCreateDto);
    DonationDto update(DonationUpdateDto donationUpdateDto);
    void delete(int id);
}
