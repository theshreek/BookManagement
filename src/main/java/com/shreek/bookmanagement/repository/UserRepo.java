package com.shreek.bookmanagement.repository;

import com.shreek.bookmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer>{
}
