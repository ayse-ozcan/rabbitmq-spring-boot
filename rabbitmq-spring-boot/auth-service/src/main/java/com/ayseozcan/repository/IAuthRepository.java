package com.ayseozcan.repository;

import com.ayseozcan.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthRepository extends JpaRepository<Auth, Long> {
}
