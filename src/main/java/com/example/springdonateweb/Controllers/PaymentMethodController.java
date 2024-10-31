package com.example.springdonateweb.Controllers;

import com.example.springdonateweb.Models.Dtos.Paymentmethods.PaymentMethodCreateDto;
import com.example.springdonateweb.Models.Dtos.Paymentmethods.PaymentMethodResponseDto;
import com.example.springdonateweb.Models.Dtos.Paymentmethods.PaymentMethodUpdateDto;
import com.example.springdonateweb.Services.interfaces.IPaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/paymentmethods")
public class PaymentMethodController {

    private final IPaymentMethodService paymentMethodService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("paymentMethods", paymentMethodService.findAll());
        return "admin/PaymentMethods/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("paymentMethod", new PaymentMethodCreateDto());
        return "admin/PaymentMethods/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute PaymentMethodCreateDto paymentMethodCreateDto) {
        paymentMethodService.create(paymentMethodCreateDto);
        return "redirect:/admin/paymentmethods";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        PaymentMethodResponseDto paymentMethod = paymentMethodService.findById(id);
        if (paymentMethod == null) return "redirect:/admin/paymentmethods";
        model.addAttribute("paymentMethod", paymentMethod);
        return "admin/PaymentMethods/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id, @ModelAttribute PaymentMethodUpdateDto paymentMethodUpdateDto) {
        paymentMethodService.update(id, paymentMethodUpdateDto);
        return "redirect:/admin/paymentmethods";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        paymentMethodService.delete(id);
        return "redirect:/admin/paymentmethods";
    }
}
