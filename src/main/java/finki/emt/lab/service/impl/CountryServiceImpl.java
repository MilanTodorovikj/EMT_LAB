package finki.emt.lab.service.impl;

import finki.emt.lab.model.domain.Country;
import finki.emt.lab.model.exception.CountryNotFoundException;
import finki.emt.lab.repository.CountryRepository;
import finki.emt.lab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        Country country = this.countryRepository.findById(id).orElseThrow(()-> new CountryNotFoundException(id));
        return country;
    }
}
