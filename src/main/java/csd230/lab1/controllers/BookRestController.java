package csd230.lab1.controllers;

import csd230.lab1.entities.BookEntity;
import csd230.lab1.repositories.BookEntityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Book REST API", description = "JSON API for managing books")
@RestController
@RequestMapping("/api/rest/books")
@CrossOrigin(origins = "*")
public class BookRestController {
    private final BookEntityRepository repository;

    public BookRestController(BookEntityRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Get all books", description = "Returns a list of all books in the database")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of books")
    @GetMapping
    public List<BookEntity> all() {
        return repository.findAll();
    }

    @Operation(summary = "Create a new book", description = "Adds a new book to the database")
    @ApiResponse(responseCode = "200", description = "Successfully created a new book")
    @PostMapping
    public BookEntity newBook(@RequestBody BookEntity newBook) {
        return repository.save(newBook);
    }

    @Operation(summary = "Get a book by ID", description = "Returns a single book based on the provided ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the book")
    @GetMapping("/{id}")
    public BookEntity one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @Operation(summary = "Replace a book", description = "Updates) an existing book with the provided ID using the new book data")
    @ApiResponse(responseCode = "200", description = "Successfully replaced the book")
    @PutMapping("/{id}")
    public BookEntity replaceBook(@RequestBody BookEntity newBook, @PathVariable Long id) {
        return repository.findById(id)
                .map(book -> {
                    book.setTitle(newBook.getTitle());
                    book.setAuthor(newBook.getAuthor());
                    book.setPrice(newBook.getPrice());
                    book.setCopies(newBook.getCopies());
                    return repository.save(book);
                })
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Operation(summary = "Delete a book", description = "Removes a book from the database based on the provided ID")
    @ApiResponse(responseCode = "200", description = "Successfully deleted the book")
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }
}