package com.dataacesso.api.service;

import com.dataacesso.api.exception.UserNotFoundException;
import com.dataacesso.api.model.User;
import com.dataacesso.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(User users){
        return userRepository.save(users);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User findById(Long id) throws UserNotFoundException {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()){
            return opt.get();
        } else {
            throw new UserNotFoundException("Usuario com id : " + id + " Não Existe" );
        }
    }

    public User update(Long id, User user) throws UserNotFoundException{
        User userSave = findById(id);
        userSave.setName(user.getName());
        userSave.setPassword(user.getPassword());
        return userRepository.save(userSave);
    }

    public void delete(Long id) throws UserNotFoundException{
        User user = findById(id);
        userRepository.delete(user);
    }
}
