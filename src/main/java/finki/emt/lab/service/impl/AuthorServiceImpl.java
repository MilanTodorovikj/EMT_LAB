package finki.emt.lab.service.impl;

import finki.emt.lab.model.exception.AuthorNotFoundException;
import finki.emt.lab.model.domain.Author;
import finki.emt.lab.repository.AuthorRepository;
import finki.emt.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        Author author = this.authorRepository.findById(id).orElseThrow(()-> new AuthorNotFoundException(id));

        return author;
    }
}
