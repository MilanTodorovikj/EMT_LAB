package finki.emt.lab.service;

import finki.emt.lab.model.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    Author findById(Long id);
}
