package com.example.springdonateweb.Services;

import com.example.springdonateweb.Models.Dtos.Donations.DonationCreateDto;
import com.example.springdonateweb.Models.Dtos.Donations.DonationResponseDto;
import com.example.springdonateweb.Models.Dtos.Donations.DonationUpdateDto;
import com.example.springdonateweb.Models.Entities.DonationsEntity;
import com.example.springdonateweb.Repositories.DonationsRepository;
import com.example.springdonateweb.Services.interfaces.IDonationsService;
import com.example.springdonateweb.Services.mappers.DonationsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonationsService implements IDonationsService {

    private final DonationsRepository donationsRepository;
    private final DonationsMapper donationsMapper;
    private final com.example.springdonateweb.Repositories.TransactionsRepository transactionsRepository;
    private final com.example.springdonateweb.Repositories.ProgramsRepository programsRepository;
    private final com.example.springdonateweb.Repositories.UsersRepository usersRepository;
    private final com.example.springdonateweb.Repositories.PaymentMethodRepository paymentMethodRepository;

    private DonationResponseDto enhanceDto(DonationResponseDto dto) {
        if (dto == null)
            return null;

        if (dto.getProgramId() != null) {
            programsRepository.findById(dto.getProgramId()).ifPresent(p -> dto.setProgramName(p.getName()));
        }
        if (dto.getUserId() != null) {
            usersRepository.findById(dto.getUserId()).ifPresent(u -> dto.setUserEmail(u.getEmail()));
        }

        // Find transaction for this donation
        if (dto.getDonationId() != null) {
            transactionsRepository.findByDonationId(dto.getDonationId()).ifPresent(tx -> {
                dto.setStatus(tx.getStatus());
                if (tx.getPaymentMethodId() != null) {
                    paymentMethodRepository.findById(tx.getPaymentMethodId())
                            .ifPresent(pm -> dto.setPaymentMethod(pm.getMethodName()));
                }
            });
        }

        return dto;
    }

    public Map<Integer, BigDecimal> getTotalDonationsByProgram() {
        // Lấy tất cả các khoản quyên góp
        List<DonationResponseDto> donations = donationsRepository.findAll().stream()
                .map(donationEntity -> DonationResponseDto.builder()
                        .donationId(donationEntity.getDonationId())
                        .userId(donationEntity.getUserId())
                        .programId(donationEntity.getProgramId())
                        .amount(donationEntity.getAmount())
                        .donationDate(donationEntity.getDonationDate())
                        .donorName(donationEntity.getDonorName())
                        .build())
                .collect(Collectors.toList());

        // Nhóm theo programId và tính tổng số tiền quyên góp cho mỗi chương trình
        Map<Integer, BigDecimal> programTotalDonations = donations.stream()
                .collect(Collectors.groupingBy(
                        DonationResponseDto::getProgramId,
                        Collectors.reducing(BigDecimal.ZERO, DonationResponseDto::getAmount, BigDecimal::add)));

        System.out.println("Total Donations by Program: " + programTotalDonations); // Log để kiểm tra dữ liệu
        System.out.println("Donations Data: " + donations);
        System.out.println("Total Donations by Program: " + programTotalDonations);
        return programTotalDonations;
    }

    @Override
    public List<Map<String, Object>> getTopDonors(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return donationsRepository.findTopDonors(pageable);
    }

    @Override
    public Map<String, BigDecimal> getTotalDonationsByDay() {
        return donationsRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        donation -> donation.getDonationDate().toLocalDate().toString(), // Group by date
                        Collectors.reducing(BigDecimal.ZERO, DonationsEntity::getAmount, BigDecimal::add)));
    }

    @Override
    public List<DonationResponseDto> findAll() {
        return donationsRepository.findAll().stream()
                .map(donationsMapper::toDto)
                .map(this::enhanceDto)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getTotalDonations() {
        BigDecimal total = donationsRepository.getTotalDonations();
        return total != null ? total : BigDecimal.ZERO;
    }

    @Override
    public long countDistinctDonors() {
        return donationsRepository.countDistinctDonors();
    }

    @Override
    public BigDecimal getAverageDonation() {
        BigDecimal total = donationsRepository.getTotalDonations();
        long count = donationsRepository.countTotalDonations();
        if (total == null || count == 0)
            return BigDecimal.ZERO;
        return total.divide(BigDecimal.valueOf(count), 2, java.math.RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getMonthlyDonations() {
        BigDecimal monthly = donationsRepository.getMonthlyDonations();
        return monthly != null ? monthly : BigDecimal.ZERO;
    }

    @Override
    public Page<DonationResponseDto> findDonationsByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DonationsEntity> donationPage = donationsRepository.findAll(pageable);
        return donationPage.map(donationsMapper::toDto).map(this::enhanceDto);
    }

    @Override
    public DonationResponseDto findById(int id) {
        return donationsRepository.findById(id)
                .map(donationsMapper::toDto)
                .map(this::enhanceDto)
                .orElse(null);
    }

    @Override
    public DonationResponseDto create(DonationCreateDto donationCreateDto) {
        DonationsEntity donationsEntity = donationsMapper.toEntity(donationCreateDto);
        DonationsEntity savedDonation = donationsRepository.save(donationsEntity);
        return donationsMapper.toDto(savedDonation);
    }

    @Override
    public DonationResponseDto update(int id, DonationUpdateDto donationUpdateDto) {
        return donationsRepository.findById(id)
                .map(existingDonation -> {
                    DonationsEntity updatedDonation = donationsMapper.partialUpdate(donationUpdateDto,
                            existingDonation);
                    return donationsMapper.toDto(donationsRepository.save(updatedDonation));
                })
                .orElse(null);
    }

    @Override
    public void delete(int id) {
        donationsRepository.deleteById(id);
    }

    @Override
    public List<DonationResponseDto> findByUserId(int userId) {
        return donationsRepository.findByUserId(userId).stream()
                .map(donationsMapper::toDto)
                .map(this::enhanceDto)
                .collect(Collectors.toList());
    }
}
