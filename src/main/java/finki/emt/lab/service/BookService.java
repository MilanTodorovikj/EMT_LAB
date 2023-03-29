package finki.emt.lab.service;

import finki.emt.lab.model.domain.Book;
import finki.emt.lab.model.dto.BookDto;
import finki.emt.lab.model.enumeration.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Page<Book> findAllPageable(Pageable pageable);

    Optional<Book> findById(Long id);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, BookDto bookDto);

    Optional<Book> deleteById(Long id);

    Optional<Book> markAsRented(Long id);
}
