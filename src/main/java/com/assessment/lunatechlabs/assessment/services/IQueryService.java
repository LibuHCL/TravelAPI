/**
 * 
 */
package com.assessment.lunatechlabs.assessment.services;

import java.util.List;

import com.assessment.lunatechlabs.domain.Airport;

/**
 * @author Libu
 *
 */
public interface IQueryService {
	
	List<Airport> getAirportByCode(String code);
	List<Airport> getAirportByName(String name);

}
