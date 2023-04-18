package finki.emt.lab.config;

import finki.emt.lab.model.domain.Author;
import finki.emt.lab.model.domain.Book;
import finki.emt.lab.model.domain.Country;
import finki.emt.lab.model.enumeration.Category;
import finki.emt.lab.repository.AuthorRepository;
import finki.emt.lab.repository.BookRepository;
import finki.emt.lab.repository.CountryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    private List<Author> authors = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private List<Country> countries = new ArrayList<>();
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CountryRepository countryRepository;

    public DataHolder(AuthorRepository authorRepository, BookRepository bookRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init(){
        Country c1 = new Country("Macedonia","Europe");
        Country c2 = new Country("Serbia","Europe");
        Country c3 = new Country("Boston","North America");
        Country c4= new Country("New York","North America");
        countries.add(c1);
        countries.add(c2);
        countries.add(c3);
        countries.add(c4);
        countryRepository.saveAll(countries);

        Author a1 = new Author("Milan","Todorovikj",c1);
        Author a2 = new Author("Nebojsha","Todorovikj",c2);
        Author a3 = new Author("Filip","Trajkovikj",c3);
        Author a4 = new Author("Petar","Stojanovikj",c4);
        Author a5 = new Author("Ana","Savikj",c4);
        authors.add(a1);
        authors.add(a2);
        authors.add(a3);
        authors.add(a4);
        authors.add(a5);
        authorRepository.saveAll(authors);

        Book b1 = new Book("Book 1", Category.FANTASY,a1,3);
        Book b2 = new Book("Book 2", Category.DRAMA,a2,15);
        Book b3 = new Book("Book 3", Category.BIOGRAPHY,a3,55);
        Book b4 = new Book("Book 4", Category.NOVEL,a1,16);
        Book b5 = new Book("Book 5", Category.THRILLER,a1,80);
        Book b6 = new Book("Book 6", Category.HISTORY,a2,500);
        Book b7 = new Book("Book 7", Category.CLASSICS,a4,52);
        Book b8 = new Book("Book 8", Category.CLASSICS,a5,100);
        Book b9 = new Book("Book 9", Category.DRAMA,a4,200);
        Book b10 = new Book("Book 10", Category.FANTASY,a5,300);
        books.add(b1);
        books.add(b2);
        books.add(b3);
        books.add(b4);
        books.add(b5);
        books.add(b6);
        books.add(b7);
        books.add(b8);
        books.add(b9);
        books.add(b10);
        bookRepository.saveAll(books);
    }
}
