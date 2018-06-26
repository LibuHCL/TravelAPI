/**
 * 
 */
package com.assessment.lunatechlabs.assessment.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.assessment.lunatechlabs.assessment.services.IAirportService;
import com.assessment.lunatechlabs.domain.Airport;
import com.assessment.lunatechlabs.domain.Country;
import com.assessment.lunatechlabs.repository.AirportRepository;

import lombok.NonNull;
import springfox.documentation.annotations.Cacheable;

/**
 * @author Libu
 *
 */
@Service
@CacheConfig(cacheNames = {"query", "report"})
public class AirportService implements IAirportService {

	@Autowired
	private AirportRepository airportRepository;

	@Override
	public List<Airport> findAirports(@NonNull Country country) {

		return airportRepository.findByCountryId(country.getId());
	}

	@Override
	@Cacheable(value = "#countries.isNotEmpty()")
	public List<Airport> findAirports(@NonNull List<Country> countries) {

		List<Long> countryIds = countries.stream().map(c -> c.getId()).collect(Collectors.toList());
		return airportRepository.findByCountryIdIn(countryIds);
	}

}
