package com.dataacesso.api.controller;

import com.dataacesso.api.dto.RegisterCreateDTO;
import com.dataacesso.api.dto.RegisterResponseDTO;
import com.dataacesso.api.dto.mapper.RegisterMapper;
import com.dataacesso.api.exception.RegisterNotFoundException;
import com.dataacesso.api.model.Register;
import com.dataacesso.api.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private RegisterMapper registerMapper;

    @PostMapping
    public ResponseEntity<RegisterResponseDTO> create (@RequestBody @Valid RegisterCreateDTO registerCreateDTO){
        Register register = registerMapper.toEntity(registerCreateDTO);
        Register registerCreate = registerService.create(register);
        RegisterResponseDTO registerResponseDTO = registerMapper.toDTO(registerCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponseDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RegisterResponseDTO>> findAll(){
        List<Register> registers = registerService.findAll();
        List<RegisterResponseDTO> registerResponseDTO = registerMapper.toDTO(registers);
        return ResponseEntity.status(HttpStatus.OK).body(registerResponseDTO);
    }
    @GetMapping("/find/{name}")
    public ResponseEntity<Object> findByName(@PathVariable(value = "name") String name) throws RegisterNotFoundException {
        List<Register> registers = (List<Register>) registerService.findByName(name);

        if (registers == null || registers.isEmpty()) {
            throw new RegisterNotFoundException("Nenhum registro encontrado com o nome: " + name);
        }

        List<RegisterResponseDTO> registerResponseDTO = registerMapper.toDTO(registers);
        return ResponseEntity.status(HttpStatus.OK).body(registerResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id)throws RegisterNotFoundException{
        registerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deletado item com Id: " + id);
    }
}
