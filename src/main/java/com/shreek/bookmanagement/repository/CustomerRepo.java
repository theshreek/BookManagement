package com.shreek.bookmanagement.repository;

import com.shreek.bookmanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
}
