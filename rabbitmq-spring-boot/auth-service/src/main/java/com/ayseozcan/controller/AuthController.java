package com.ayseozcan.controller;

import com.ayseozcan.dto.SaveRequestDto;
import com.ayseozcan.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody SaveRequestDto dto) {
        return ResponseEntity.ok(authService.save(dto));
    }
}
