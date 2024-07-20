package com.mpphw.springbootBackend.service;

import com.mpphw.springbootBackend.model.Book;
import com.mpphw.springbootBackend.model.BookNote;
import com.mpphw.springbootBackend.model.User;
import com.mpphw.springbootBackend.repository.BookRepository;
import com.mpphw.springbootBackend.repository.BookNoteRepository;
import com.mpphw.springbootBackend.repository.UserRepository;
import com.mpphw.springbootBackend.sanitizers.InputSanitizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookNoteRepository bookNoteRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookService(BookRepository bookRepository, BookNoteRepository bookNoteRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.bookNoteRepository = bookNoteRepository;
        this.userRepository = userRepository;
    }

    private User getAuthenticatedUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username).orElseThrow(() -> new Exception("User not found"));
    }

    public List<Book> getBooks() throws Exception {
        User user = getAuthenticatedUser();
        return user.getBooks();
    }

    public void addBook(Book newBook) throws Exception {
        String bookTitle = newBook.getTitle();
        bookTitle = InputSanitizer.sanitizeHtml(bookTitle);
        InputSanitizer.sanitizeCommandLine(bookTitle);

        String bookAuthor = newBook.getAuthor();
        bookAuthor = InputSanitizer.sanitizeHtml(bookAuthor);
        InputSanitizer.sanitizeCommandLine(bookAuthor);

        String bookGenre = newBook.getGenre();
        bookGenre = InputSanitizer.sanitizeHtml(bookGenre);
        InputSanitizer.sanitizeCommandLine(bookGenre);

        User user = getAuthenticatedUser();
        newBook.setUser(user);
        this.bookRepository.save(newBook);
    }

    public Book getDetailsBook(Integer bookId) throws Exception {
        return this.bookRepository.findById(bookId).orElseThrow(() -> new Exception("Book not found"));
    }

    public void deleteBook(Integer bookId) throws Exception {
        if(this.bookRepository.existsById(bookId))
        {
            this.bookRepository.deleteById(bookId);
        }
        else
            throw  new Exception("Book not found");
    }

    public void updateBook(Integer bookId, Book updatedBook) throws Exception {
        String updatedBookTitle = updatedBook.getTitle();
        updatedBookTitle = InputSanitizer.sanitizeHtml(updatedBookTitle);
        updatedBookTitle = InputSanitizer.sanitizeCommandLine(updatedBookTitle);

        String updatedBookAuthor = updatedBook.getAuthor();
        updatedBookAuthor = InputSanitizer.sanitizeHtml(updatedBookAuthor);
        updatedBookAuthor = InputSanitizer.sanitizeCommandLine(updatedBookAuthor);

        String updatedBookGenre = updatedBook.getGenre();
        updatedBookGenre = InputSanitizer.sanitizeHtml(updatedBookGenre);
        updatedBookGenre = InputSanitizer.sanitizeCommandLine(updatedBookGenre);

        Book foundBook = bookRepository.findById(bookId).orElseThrow(() -> new Exception("Book not found"));
        foundBook.setTitle(updatedBookTitle);
        foundBook.setAuthor(updatedBookAuthor);
        foundBook.setGenre(updatedBookGenre);
        foundBook.setNrPages(updatedBook.getNrPages());
        this.bookRepository.save(foundBook);
    }

    public List<BookNote> getBookNotes(Integer bookId) throws Exception
    {
        Book book = this.bookRepository.findById(bookId).orElseThrow(() -> new Exception("Book not found"));
        return book.getNotes();
    }

    public void addBookNote(Integer bookId, BookNote newBookNote) throws Exception {
        String noteTitle = newBookNote.getNoteTitle();
        noteTitle = InputSanitizer.sanitizeHtml(noteTitle);
        InputSanitizer.sanitizeCommandLine(noteTitle);

        String noteDescription = newBookNote.getNoteDescription();
        noteDescription = InputSanitizer.sanitizeHtml(noteDescription);
        InputSanitizer.sanitizeCommandLine(noteDescription);

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new Exception("Book not found"));
        newBookNote.setBook(book);
        bookNoteRepository.save(newBookNote);
    }

    public BookNote getDetailsBookNote(int id) throws Exception {
        return this.bookNoteRepository.findById(id).orElseThrow(() -> new Exception("Book note not found"));
    }

    public void deleteBookNote(int id) throws Exception {
        if(this.bookNoteRepository.existsById(id))
        {
            this.bookNoteRepository.deleteById(id);
        }
        else
            throw new Exception("Book note not found");
    }

    public void updateBookNote(int id, BookNote updatedBookNote) throws Exception {
        String noteTitle = updatedBookNote.getNoteTitle();
        noteTitle = InputSanitizer.sanitizeHtml(noteTitle);
        noteTitle = InputSanitizer.sanitizeCommandLine(noteTitle);

        String noteDescription = updatedBookNote.getNoteDescription();
        noteDescription = InputSanitizer.sanitizeHtml(noteDescription);
        noteDescription = InputSanitizer.sanitizeCommandLine(noteDescription);

        BookNote foundBookNote = this.bookNoteRepository.findById(id).orElseThrow(() -> new Exception("Book note not found"));
        foundBookNote.setNoteTitle(noteTitle);
        foundBookNote.setNoteDescription(noteDescription);
        this.bookNoteRepository.save(foundBookNote);
    }
}
