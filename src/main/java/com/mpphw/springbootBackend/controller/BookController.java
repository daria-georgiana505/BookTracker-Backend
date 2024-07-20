package com.mpphw.springbootBackend.controller;

import com.mpphw.springbootBackend.model.Book;
import com.mpphw.springbootBackend.model.BookNote;
import com.mpphw.springbootBackend.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<Object> getBooks() throws Exception {
        return new ResponseEntity<>(this.bookService.getBooks(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addBook(@Valid @RequestBody Book addedBook, BindingResult bindingResult) throws Exception {
        Map<String, Object> responseBody = new HashMap<>();

        if (bindingResult.hasErrors()) {
            responseBody.put("error", "Validation failed");
            responseBody.put("details", bindingResult.getAllErrors());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        this.bookService.addBook(addedBook);
        responseBody.put("message", "Add operation was done successfully");
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Object> getDetailsBook(@PathVariable(value = "id") Integer id) throws Exception {
        return new ResponseEntity<>(this.bookService.getDetailsBook(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable(value = "id") Integer id) throws Exception {
        Map<String, Object> responseBody = new HashMap<>();
        this.bookService.deleteBook(id);
        responseBody.put("message", "Delete operation was done successfully");
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable(value = "id") Integer id, @Valid @RequestBody Book updatedBook, BindingResult bindingResult) throws Exception {
        Map<String, Object> responseBody = new HashMap<>();

        if (bindingResult.hasErrors()) {
            responseBody.put("error", "Validation failed");
            responseBody.put("details", bindingResult.getAllErrors());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        this.bookService.updateBook(id, updatedBook);
        responseBody.put("message", "Update operation was done successfully");
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/allNotes/{id}")
    public ResponseEntity<Object> getBookNotes(@PathVariable(value = "id") Integer id) throws Exception {
        return new ResponseEntity<>(this.bookService.getBookNotes(id), HttpStatus.OK);
    }

    @PostMapping("/addNote/{id}")
    public ResponseEntity<Object> addBookNote(@PathVariable(value = "id") Integer id, @Valid @RequestBody BookNote addedBookNote, BindingResult bindingResult) throws Exception {
        Map<String, Object> responseBody = new HashMap<>();

        if (bindingResult.hasErrors()) {
            responseBody.put("error", "Validation failed");
            responseBody.put("details", bindingResult.getAllErrors());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        this.bookService.addBookNote(id, addedBookNote);
        responseBody.put("message", "Add operation was done successfully");
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/detailsNote/{id}")
    public ResponseEntity<Object> getDetailsBookNote(@PathVariable(value = "id") Integer id) throws Exception {
        return new ResponseEntity<>(this.bookService.getDetailsBookNote(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteNote/{id}")
    public ResponseEntity<Object> deleteBookNote(@PathVariable(value = "id") Integer id) throws Exception {
        Map<String, Object> responseBody = new HashMap<>();
        this.bookService.deleteBookNote(id);
        responseBody.put("message", "Delete operation was done successfully");
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PutMapping("/updateNote/{id}")
    public ResponseEntity<Object> updateBookNote(@PathVariable(value = "id") Integer id, @Valid @RequestBody BookNote updatedBookNote, BindingResult bindingResult) throws Exception {
        Map<String, Object> responseBody = new HashMap<>();

        if (bindingResult.hasErrors()) {
            responseBody.put("error", "Validation failed");
            responseBody.put("details", bindingResult.getAllErrors());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        this.bookService.updateBookNote(id, updatedBookNote);
        responseBody.put("message", "Update operation was done successfully");
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
