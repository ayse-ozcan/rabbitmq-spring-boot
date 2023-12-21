package com.ayseozcan.service;

import com.ayseozcan.dto.SaveRequestDto;
import com.ayseozcan.rabbitmq.model.RegisterModel;
import com.ayseozcan.rabbitmq.producer.RegisterProducer;
import com.ayseozcan.repository.IAuthRepository;
import com.ayseozcan.repository.entity.Auth;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final IAuthRepository authRepository;
    private final RegisterProducer registerProducer;

    public AuthService(IAuthRepository authRepository, RegisterProducer registerProducer) {
        this.authRepository = authRepository;
        this.registerProducer = registerProducer;
    }

    public Boolean save(SaveRequestDto dto) {
        Auth auth = new Auth();
        auth.setName(dto.getName());
        auth.setSurname(dto.getSurname());
        auth.setEmail(dto.getEmail());
        auth.setPassword(dto.getPassword());
        authRepository.save(auth);

        RegisterModel registerModel = new RegisterModel();
        registerModel.setAuthId(auth.getId());
        registerModel.setName(auth.getName());
        registerModel.setSurname(auth.getSurname());
        registerModel.setEmail(auth.getEmail());
        registerModel.setPassword(auth.getPassword());
        registerProducer.sendRegisterMessage(registerModel);
        return true;
    }
}
