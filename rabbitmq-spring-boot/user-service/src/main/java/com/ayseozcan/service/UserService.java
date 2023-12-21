package com.ayseozcan.service;

import com.ayseozcan.rabbitmq.model.RegisterModel;
import com.ayseozcan.repository.IUserRepository;
import com.ayseozcan.repository.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(RegisterModel registerModel) {
        User user = new User();
        user.setAuthId(registerModel.getAuthId());
        user.setName(registerModel.getName());
        user.setSurname(registerModel.getSurname());
        user.setEmail(registerModel.getEmail());
        user.setPassword(registerModel.getPassword());
        userRepository.save(user);
    }
}
