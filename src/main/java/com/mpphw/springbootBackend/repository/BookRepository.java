package com.mpphw.springbootBackend.repository;

import com.mpphw.springbootBackend.model.Book;
import com.mpphw.springbootBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}