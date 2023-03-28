package com.shreek.bookmanagement.controller;

import com.shreek.bookmanagement.dto.ResponseDTO;
import com.shreek.bookmanagement.model.Book;
import com.shreek.bookmanagement.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookRepo bookRepo;
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book newbook = new Book();
        newbook.setBookName(book.getBookName());
        newbook.setPublisher(book.getPublisher());
        newbook.setQuantity(book.getQuantity());
        newbook.setPrice(book.getPrice());

        Book added = bookRepo.save(newbook);
        return new  ResponseEntity<Book>(added,HttpStatus.ACCEPTED);
    }
    @GetMapping
    public List<Book> getAllBook(){
        return bookRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable("id") int id){
        return Optional.ofNullable(bookRepo.findById(id).orElseThrow(() ->
                new Error("Book not found By Id" + id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookById(@RequestBody Book book, @PathVariable("id") int id){
        Book oldbook = bookRepo.findById(id).orElseThrow(()->
                new Error("Book Not found By id"+id));
        oldbook.setBookName(book.getBookName());
        oldbook.setPublisher(book.getPublisher());
        oldbook.setQuantity(book.getQuantity());
        oldbook.setPrice(book.getPrice());
        Book update = bookRepo.save(oldbook);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteBookById(@PathVariable("id") int id){
        Optional<Book> book = bookRepo.findById(id);
        if(book.isPresent()) bookRepo.deleteById(id);
        else throw new Error("Book Not Found BY id");
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Book successfully delete");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }



}
