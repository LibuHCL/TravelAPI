/**
 * 
 */
package com.assessment.lunatechlabs.assessment.services;

import java.util.List;

import com.assessment.lunatechlabs.domain.Airport;
import com.assessment.lunatechlabs.domain.Country;
import com.assessment.lunatechlabs.domain.Runway;

/**
 * @author Libu
 *
 */
public interface IRunwayService {
	
	List<Runway> fetchRunwaysForAirport(Airport airport);
	List<Runway> fetchRunwaysForAirports(List<Airport> airports);
	List<Runway> fetchRunwaysForCountry(Country country);
	List<Runway> fetchRunwaysForCountries(List<Country> countries);

}
