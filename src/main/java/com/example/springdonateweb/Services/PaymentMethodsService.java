package com.example.springdonateweb.Services;

import com.example.springdonateweb.Models.Dtos.PaymentMethods.PaymentMethodCreateDto;
import com.example.springdonateweb.Models.Dtos.PaymentMethods.PaymentMethodDto;
import com.example.springdonateweb.Models.Dtos.PaymentMethods.PaymentMethodUpdateDto;
import com.example.springdonateweb.Models.Entities.PaymentmethodsEntity;
import com.example.springdonateweb.Repositories.PaymentMethodsRepository;
import com.example.springdonateweb.Services.interfaces.IPaymentMethodsService;
import com.example.springdonateweb.Services.mappers.PaymentmethodsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentMethodsService implements IPaymentMethodsService {

    private final PaymentMethodsRepository paymentMethodsRepository;
    private final PaymentmethodsMapper paymentMethodsMapper;

    @Override
    public List<PaymentMethodDto> findAll() {
        return paymentMethodsRepository.findAll().stream()
                .map(paymentMethodsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentMethodDto findById(int id) {
        Optional<PaymentmethodsEntity> paymentMethod = paymentMethodsRepository.findById(id);
        return paymentMethod.map(paymentMethodsMapper::toDto).orElse(null);
    }

    @Override
    public PaymentMethodDto create(PaymentMethodCreateDto paymentMethodCreateDto) {
        PaymentmethodsEntity paymentMethod = paymentMethodsMapper.toEntity(paymentMethodCreateDto);
        PaymentmethodsEntity savedPaymentMethod = paymentMethodsRepository.save(paymentMethod);
        return paymentMethodsMapper.toDto(savedPaymentMethod);
    }

    @Override
    public PaymentMethodDto update(PaymentMethodUpdateDto paymentMethodUpdateDto) {
        Optional<PaymentmethodsEntity> paymentMethod = paymentMethodsRepository.findById(paymentMethodUpdateDto.getPaymentMethodId());
        return paymentMethod
                .map(pm -> {
                    PaymentmethodsEntity updatedPaymentMethod = paymentMethodsMapper.partialUpdate(paymentMethodUpdateDto, pm);
                    PaymentmethodsEntity result = paymentMethodsRepository.save(updatedPaymentMethod);
                    return paymentMethodsMapper.toDto(result);
                })
                .orElse(null);
    }

    @Override
    public void delete(int id) {
        paymentMethodsRepository.deleteById(id);
    }
}
