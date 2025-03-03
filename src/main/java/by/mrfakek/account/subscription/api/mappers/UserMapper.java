package by.mrfakek.account.subscription.api.mappers;

import by.mrfakek.account.subscription.api.dtos.UserCreateDto;
import by.mrfakek.account.subscription.api.dtos.UserResponseDto;
import by.mrfakek.account.subscription.api.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toUserResponseDto(UserEntity user);
    UserEntity toUserEntity(UserCreateDto userDto);
    UserEntity updateUserEntity(UserCreateDto userCreateDtoDto,@MappingTarget UserEntity userEntity);
}
