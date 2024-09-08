package com.dataacesso.api.controller;

import com.dataacesso.api.dto.UserCreateDTO;
import com.dataacesso.api.dto.UserResponseDTO;
import com.dataacesso.api.dto.mapper.UserMapper;
import com.dataacesso.api.exception.UserNotFoundException;
import com.dataacesso.api.model.User;
import com.dataacesso.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create( @RequestBody @Valid UserCreateDTO userCreateDTO){
        User users = userMapper.toEntity(userCreateDTO);
        User userCreate = userService.create(users);
        UserResponseDTO userResponseDTO = userMapper.toDTO(userCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>>findAll(){
        List<User> users = userService.findAll();
        List<UserResponseDTO> userResponseDTO = userMapper.toDTO(users);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id")Long id) throws UserNotFoundException {

        User user = userService.findById(id);
        UserResponseDTO userResponseDTO = userMapper.toDTO(user);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id")Long id,
                                         @RequestBody @Valid UserCreateDTO userCreateDTO)throws UserNotFoundException{

        User user = userMapper.toEntity(userCreateDTO);
        User userSave = userService.update(id, user );
        UserResponseDTO userResponseDTO = userMapper.toDTO(userSave);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id){
        try {
            userService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado!!");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
