package by.mrfakek.account.subscription.api.services;

import by.mrfakek.account.subscription.api.dtos.UserCreateDto;
import by.mrfakek.account.subscription.api.dtos.UserResponseDto;
import by.mrfakek.account.subscription.api.entities.UserEntity;
import by.mrfakek.account.subscription.api.exceptions.BadRequestException;
import by.mrfakek.account.subscription.api.exceptions.NotFoundException;
import by.mrfakek.account.subscription.api.mappers.UserMapper;
import by.mrfakek.account.subscription.api.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDto getUserById(Long id) {
        log.info("getUserById id : {} ",id);
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User",id));
        log.info("user by id : {} found ",id );
        return userMapper.toUserResponseDto(user);
    }

    public UserResponseDto createUser(UserCreateDto userCreateDto) {
        log.info("create user with username : {}", userCreateDto.getUsername());
        UserEntity user = userMapper.toUserEntity(userCreateDto);
        user = userRepository.save(user);
        log.info("user created with id : {}", user.getId());
        return userMapper.toUserResponseDto(user);
    }

    public UserResponseDto updateUser(Long id, UserCreateDto userCreateDtoDto) {
        log.info("update user with id : {}", id);
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User",id));
        if (user.getUsername().equals(userCreateDtoDto.getUsername())){
            throw new BadRequestException("Username matches current");}
        user = userMapper.updateUserEntity(userCreateDtoDto, user);
        user = userRepository.save(user);
        log.info("user updated with id : {}", user.getId());
        return userMapper.toUserResponseDto(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
}
