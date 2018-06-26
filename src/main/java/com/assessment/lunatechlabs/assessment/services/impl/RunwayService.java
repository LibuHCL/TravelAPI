/**
 * 
 */
package com.assessment.lunatechlabs.assessment.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.lunatechlabs.assessment.services.IRunwayService;
import com.assessment.lunatechlabs.domain.Airport;
import com.assessment.lunatechlabs.domain.Country;
import com.assessment.lunatechlabs.domain.Runway;
import com.assessment.lunatechlabs.repository.RunwayRepository;

/**
 * @author Libu
 *
 */
@Service
public class RunwayService implements IRunwayService {
	
	@Autowired
    private RunwayRepository runwayRepository;

	@Override
	public List<Runway> fetchRunwaysForAirport(Airport airport) {
		 return runwayRepository.findByAirportId(airport.getId());
	}

	@Override
	public List<Runway> fetchRunwaysForAirports(List<Airport> airports) {
		List<Long> airportIds = airports.stream().map(a -> a.getId()).collect(Collectors.toList());
        return runwayRepository.findByAirportIdIn(airportIds);
	}

	@Override
	public List<Runway> fetchRunwaysForCountry(Country country) {
		return runwayRepository.findByCountryId(country.getId());
	}

	@Override
	public List<Runway> fetchRunwaysForCountries(List<Country> countries) {
		 List<Long> countryIds = countries.stream().map(a -> a.getId()).collect(Collectors.toList());
	     return runwayRepository.findByCountryIdIn(countryIds);
	}

}
