package com.example.fullstackbackend.repository;

import com.example.fullstackbackend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
