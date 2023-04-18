package finki.emt.lab.service;

import finki.emt.lab.model.domain.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();
    Country findById(Long id);
}
