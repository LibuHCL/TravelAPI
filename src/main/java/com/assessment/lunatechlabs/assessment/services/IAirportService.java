/**
 * 
 */
package com.assessment.lunatechlabs.assessment.services;

import java.util.List;

import com.assessment.lunatechlabs.domain.Airport;
import com.assessment.lunatechlabs.domain.Country;

/**
 * @author Libu
 *
 */
public interface IAirportService {
	
	List<Airport> findAirports(Country country);
	List<Airport> findAirports(List<Country> countries);

}
