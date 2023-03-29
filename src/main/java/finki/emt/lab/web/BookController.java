package finki.emt.lab.web;

import finki.emt.lab.model.domain.Book;
import finki.emt.lab.model.dto.BookDto;
import finki.emt.lab.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public List<Book> findAll() {
        return this.bookService.findAll();
    }

    @GetMapping("/all/paginated")
    public List<Book> findAllPaginated(Pageable pageable) {
        return this.bookService.findAllPageable(pageable).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
        return this.bookService.save(bookDto)
                .map(book -> ResponseEntity.ok(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return this.bookService.edit(id, bookDto)
                .map(book -> ResponseEntity.ok(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Book> book = this.bookService.deleteById(id);
        if (book.isPresent())
            return ResponseEntity.ok(book.get());
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/markAsRented/{id}")
    public ResponseEntity<Book> markAsRented(@PathVariable Long id) {
        return this.bookService.markAsRented(id)
                .map(book -> ResponseEntity.ok(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
