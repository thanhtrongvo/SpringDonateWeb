package com.example.springdonateweb.Controllers;

import com.example.springdonateweb.Models.Dtos.Transactions.TransactionAddDto;
import com.example.springdonateweb.Models.Dtos.Transactions.TransactionCreateDto;
import com.example.springdonateweb.Models.Dtos.Transactions.TransactionDto;
import com.example.springdonateweb.Models.Dtos.Transactions.TransactionUpdateDto;
import com.example.springdonateweb.Services.interfaces.ITransactionsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/transactions")
public class TransactionsController {

    private final ITransactionsService transactionsService;

    @GetMapping("")
    public String listTransactions(Model model) {
        List<TransactionDto> transactions = transactionsService.findAll();
        model.addAttribute("transactions", transactions);
        return "transactions/list";
    }

    @GetMapping("/create")
    public String createTransactionForm(Model model) {
        model.addAttribute("transaction", new TransactionCreateDto());
        return "transactions/create";
    }

    @PostMapping("/create")
    public String createTransaction(
            @Valid @ModelAttribute("transaction") TransactionCreateDto transactionCreateDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "transactions/create";
        }
        transactionsService.create(transactionCreateDto);
        redirectAttributes.addFlashAttribute("success", "Transaction created successfully");
        return "redirect:/admin/transactions";
    }

    @GetMapping("/edit/{id}")
    public String editTransactionForm(@PathVariable int id, Model model) {
        TransactionDto transaction = transactionsService.findById(id);
        if (transaction == null) {
            return "redirect:/admin/transactions";
        }
        model.addAttribute("transaction", transaction);
        return "transactions/edit";
    }

    @PostMapping("/edit/{id}")
    public String editTransaction(
            @PathVariable int id,
            @Valid @ModelAttribute("transaction") TransactionUpdateDto transactionUpdateDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "transactions/edit";
        }
        transactionsService.update(transactionUpdateDto);
        redirectAttributes.addFlashAttribute("success", "Transaction updated successfully");
        return "redirect:/admin/transactions";
    }

    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable int id, RedirectAttributes redirectAttributes) {
        transactionsService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Transaction deleted successfully");
        return "redirect:/admin/transactions";
    }
}
