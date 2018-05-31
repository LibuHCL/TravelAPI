/**
 * 
 */
package com.assessment.lunatechlabs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assessment.lunatechlabs.domain.Airport;


/**
 * @author Libu
 *
 */
public interface QueryRepository extends JpaRepository<Airport, String> {
	
	@Query(value = "SELECT * FROM travel_api.country country INNER JOIN travel_api.airport airport ON country.code = airport.ISOCountry INNER JOIN travel_api.runway runway ON airport.id = runway.airport_ref where (country.name = :countryName OR country.code =:countryCode)", nativeQuery = true)
	List<Airport> findByCountry(String countryCode,String countryName);

}
