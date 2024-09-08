package com.dataacesso.api.service;

import com.dataacesso.api.exception.RegisterNotFoundException;
import com.dataacesso.api.model.Register;
import com.dataacesso.api.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {
    @Autowired
    private RegisterRepository registerRepository;

    public Register create(Register register){
        return registerRepository.save(register);
    }

    public List<Register> findAll(){
        return registerRepository.findAll();
    }

    public List<Register> findByName(String name) {
        return registerRepository.findByName(name);
    }

    public Register findById(Long id) throws RegisterNotFoundException{
        Optional<Register> opt = registerRepository.findById(id);
        if (opt.isPresent()){
            return opt.get();
        } else {
            throw new RegisterNotFoundException("Registro com id: "+id+ "Não existe!");
        }
    }
    public void delete(Long id) throws RegisterNotFoundException{
        Register register = findById(id);
        registerRepository.delete(register);
    }


}
