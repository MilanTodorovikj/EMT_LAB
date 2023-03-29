package finki.emt.lab.service.impl;

import finki.emt.lab.model.domain.Author;
import finki.emt.lab.model.domain.Book;
import finki.emt.lab.model.dto.BookDto;
import finki.emt.lab.model.exception.AuthorNotFoundException;
import finki.emt.lab.model.exception.BookNotFoundException;
import finki.emt.lab.model.exception.BookOutOfCopiesException;
import finki.emt.lab.repository.AuthorRepository;
import finki.emt.lab.repository.BookRepository;
import finki.emt.lab.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Page<Book> findAllPageable(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> findById(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(()->new AuthorNotFoundException(bookDto.getAuthorId()));
        Book book = new Book(bookDto.getName(),bookDto.getCategory(),author,bookDto.getAvailableCopies());

        this.bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException(id));

        Author author = this.authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(()->new AuthorNotFoundException(bookDto.getAuthorId()));

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());

        this.bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public Optional<Book> deleteById(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        this.bookRepository.deleteById(id);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> markAsRented(Long id) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException(id));

        if(book.getAvailableCopies()<=0)
            throw new BookOutOfCopiesException(book.getName());

        book.setAvailableCopies(book.getAvailableCopies()-1);

        this.bookRepository.save(book);

        return Optional.of(book);
    }
}
