package com.mpphw.springbootBackend.repository;

import com.mpphw.springbootBackend.model.BookNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookNoteRepository extends JpaRepository<BookNote, Integer> {

}
