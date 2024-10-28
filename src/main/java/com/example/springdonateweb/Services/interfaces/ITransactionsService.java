package com.example.springdonateweb.Services.interfaces;

import com.example.springdonateweb.Models.Dtos.Transactions.TransactionCreateDto;
import com.example.springdonateweb.Models.Dtos.Transactions.TransactionDto;
import com.example.springdonateweb.Models.Dtos.Transactions.TransactionUpdateDto;

import java.util.List;

public interface ITransactionsService {
    List<TransactionDto> findAll();
    TransactionDto findById(int id);
    TransactionDto create(TransactionCreateDto transactionCreateDto);
    TransactionDto update(TransactionUpdateDto transactionUpdateDto);
    void delete(int id);
}
