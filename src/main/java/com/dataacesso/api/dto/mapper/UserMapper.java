package com.dataacesso.api.dto.mapper;

import com.dataacesso.api.dto.UserCreateDTO;
import com.dataacesso.api.dto.UserResponseDTO;
import com.dataacesso.api.model.User;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper mapper;

    public User toEntity(UserCreateDTO dto){
        return mapper.map(dto, User.class);
    }

    public UserResponseDTO toDTO(User entity){
        return mapper.map(entity,UserResponseDTO.class);
    }

    public List<UserResponseDTO> toDTO(List<User> users){
        return users.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
 }
