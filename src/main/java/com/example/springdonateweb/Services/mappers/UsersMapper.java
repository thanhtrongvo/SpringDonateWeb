package com.example.springdonateweb.Services.mappers;
import com.example.springdonateweb.Models.Dtos.Users.UserUpdateDto;
import com.example.springdonateweb.Models.Entities.UsersEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",uses = {UsersMapper.class})
public interface UsersMapper {
    @Mapping(target = "userId", source = "userId")
    UsersEntity toDto(UsersEntity usersEntity);
    UsersEntity toEntity(UsersEntity usersEntity);


    UsersEntity toResponseDto(UsersEntity usersEntity);



//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    UsersEntity partialUpdate(UserUpdateDto userUpdateDto, @MappingTarget UsersEntity usersEntity);
}
