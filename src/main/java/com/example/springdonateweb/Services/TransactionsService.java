package com.example.springdonateweb.Services;

import com.example.springdonateweb.Models.Dtos.Transactions.TransactionAddDto;
import com.example.springdonateweb.Models.Dtos.Transactions.TransactionCreateDto;
import com.example.springdonateweb.Models.Dtos.Transactions.TransactionDto;
import com.example.springdonateweb.Models.Dtos.Transactions.TransactionUpdateDto;
import com.example.springdonateweb.Models.Entities.TransactionsEntity;
import com.example.springdonateweb.Repositories.TransactionsRepository;
import com.example.springdonateweb.Services.interfaces.ITransactionsService;
import com.example.springdonateweb.Services.mappers.TransactionsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionsService implements ITransactionsService {

    private final TransactionsRepository transactionsRepository;
    private final TransactionsMapper transactionsMapper;

    @Override
    public List<TransactionDto> findAll() {
        return transactionsRepository.findAll().stream()
                .map(transactionsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(int id) {
        Optional<TransactionsEntity> transaction = transactionsRepository.findById(id);
        return transaction.map(transactionsMapper::toDto).orElse(null);
    }

    @Override
    public TransactionDto create(TransactionCreateDto transactionCreateDto) {
        TransactionsEntity transaction = transactionsMapper.toEntity(transactionCreateDto);
        TransactionsEntity savedTransaction = transactionsRepository.save(transaction);
        return transactionsMapper.toDto(savedTransaction);
    }

    @Override
    public TransactionDto update(TransactionUpdateDto transactionUpdateDto) {
        Optional<TransactionsEntity> transaction = transactionsRepository.findById(transactionUpdateDto.getTransactionId());
        return transaction
                .map(tr -> {
                    TransactionsEntity updatedTransaction = transactionsMapper.partialUpdate(transactionUpdateDto, tr);
                    TransactionsEntity result = transactionsRepository.save(updatedTransaction);
                    return transactionsMapper.toDto(result);
                })
                .orElse(null);
    }

    @Override
    public void delete(int id) {
        transactionsRepository.deleteById(id);
    }
}
