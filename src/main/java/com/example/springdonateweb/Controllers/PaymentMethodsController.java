package com.example.springdonateweb.Controllers;

import com.example.springdonateweb.Models.Dtos.PaymentMethods.PaymentMethodAddDto;
import com.example.springdonateweb.Models.Dtos.PaymentMethods.PaymentMethodCreateDto;
import com.example.springdonateweb.Models.Dtos.PaymentMethods.PaymentMethodDto;
import com.example.springdonateweb.Models.Dtos.PaymentMethods.PaymentMethodUpdateDto;
import com.example.springdonateweb.Services.interfaces.IPaymentMethodsService;
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
@RequestMapping("/admin/payment-methods")
public class PaymentMethodsController {

    private final IPaymentMethodsService paymentMethodsService;

    @GetMapping("")
    public String listPaymentMethods(Model model) {
        List<PaymentMethodDto> paymentMethods = paymentMethodsService.findAll();
        model.addAttribute("paymentMethods", paymentMethods);
        return "paymentmethods/list";
    }

    @GetMapping("/create")
    public String createPaymentMethodForm(Model model) {
        model.addAttribute("paymentMethod", new PaymentMethodCreateDto());
        return "paymentmethods/create";
    }

    @PostMapping("/create")
    public String createPaymentMethod(
            @Valid @ModelAttribute("paymentMethod") PaymentMethodCreateDto paymentMethodCreateDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "paymentmethods/create";
        }
        paymentMethodsService.create(paymentMethodCreateDto);
        redirectAttributes.addFlashAttribute("success", "Payment method created successfully");
        return "redirect:/admin/payment-methods";
    }

    @GetMapping("/edit/{id}")
    public String editPaymentMethodForm(@PathVariable int id, Model model) {
        PaymentMethodDto paymentMethod = paymentMethodsService.findById(id);
        if (paymentMethod == null) {
            return "redirect:/admin/payment-methods";
        }
        model.addAttribute("paymentMethod", paymentMethod);
        return "paymentmethods/edit";
    }

    @PostMapping("/edit/{id}")
    public String editPaymentMethod(
            @PathVariable int id,
            @Valid @ModelAttribute("paymentMethod") PaymentMethodUpdateDto paymentMethodUpdateDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "paymentmethods/edit";
        }
        paymentMethodsService.update(paymentMethodUpdateDto);
        redirectAttributes.addFlashAttribute("success", "Payment method updated successfully");
        return "redirect:/admin/payment-methods";
    }

    @GetMapping("/delete/{id}")
    public String deletePaymentMethod(@PathVariable int id, RedirectAttributes redirectAttributes) {
        paymentMethodsService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Payment method deleted successfully");
        return "redirect:/admin/payment-methods";
    }
}
