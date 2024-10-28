//package com.example.springdonateweb.Services.mappers;
//
//import com.example.springdonateweb.Models.Dtos.PaymentMethods.PaymentMethodAddDto;
//import com.example.springdonateweb.Models.Dtos.PaymentMethods.PaymentMethodCreateDto;
//import com.example.springdonateweb.Models.Dtos.PaymentMethods.PaymentMethodDto;
//import com.example.springdonateweb.Models.Dtos.PaymentMethods.PaymentMethodUpdateDto;
//import com.example.springdonateweb.Models.Entities.PaymentmethodsEntity;
//import org.mapstruct.*;
//
//@Mapper(componentModel = "spring")
//public interface PaymentmethodsMapper {
//    PaymentmethodsEntity toEntity(PaymentMethodCreateDto paymentMethodCreateDto);
//    PaymentmethodsEntity toEntity(PaymentMethodUpdateDto paymentMethodUpdateDto);
//    PaymentMethodDto toDto(PaymentmethodsEntity paymentmethodsEntity);
//
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    PaymentmethodsEntity partialUpdate(PaymentMethodUpdateDto paymentMethodUpdateDto, @MappingTarget PaymentmethodsEntity paymentmethodsEntity);
//}
