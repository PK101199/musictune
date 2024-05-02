package com.kodnest.music.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodnest.music.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
public User findByEmail(String email);


}
