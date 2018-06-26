package com.assessment.lunatechlabs.assessment.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.lunatechlabs.assessment.services.ICountryService;
import com.assessment.lunatechlabs.domain.Country;
import com.assessment.lunatechlabs.repository.AirportRepository;
import com.assessment.lunatechlabs.repository.CountryRepository;

import lombok.NonNull;

/**
 * @author Libu
 *
 */

@Service
public class CountryService implements ICountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private AirportRepository airportRepository;

	@Override
	public List<String> countryNamesWithPrefix(@NonNull String prefix) {

		return countryRepository.findByLowerCaseNameStartingWith(prefix.toLowerCase()).stream().map(c -> c.getName())
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Country> findCountry(@NonNull String countryParam) {

		Country country = countryRepository.findByLowerCaseName(countryParam.toLowerCase());
		if (country == null) {
			country = countryRepository.findByLowerCaseCode(countryParam.toLowerCase());
		}
		if (country == null) {
			// Bonus handling
			// If prefix matches only one country, return the same
			List<String> names = countryNamesWithPrefix(countryParam);
			if (names.size() == 1) {
				country = countryRepository.findByLowerCaseName(names.get(0).toLowerCase());
			}
		}
		return Optional.ofNullable(country);
	}

	@Override
	public List<Country> topTenCountriesWithMostNumberOfAirports() {

		List<Long> countryIds = airportRepository.countriesWithMostNumberOfAirports().stream().limit(10)
				.collect(Collectors.toList());
		return countryRepository.findByIdIn(countryIds);
	}

	@Override
	public List<Country> topTenCountriesWithLeastNumberOfAirports() {

		List<Long> countryIds = airportRepository.countriesWithLeastNumberOfAirports().stream().limit(10)
				.collect(Collectors.toList());
		return countryRepository.findByIdIn(countryIds);
	}

}
