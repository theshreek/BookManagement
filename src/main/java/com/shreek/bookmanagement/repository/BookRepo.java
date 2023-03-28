package com.shreek.bookmanagement.repository;

import com.shreek.bookmanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Integer > {

}
