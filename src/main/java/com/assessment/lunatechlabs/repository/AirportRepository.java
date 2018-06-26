/**
 * 
 */
package com.assessment.lunatechlabs.repository;

import com.assessment.lunatechlabs.domain.Airport;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The CRUD repo class which performs operations on Airport entity.
 * 
 * @author Libu
 *
 */
public interface AirportRepository extends CrudRepository<Airport, Long> {

	List<Airport> findByCountryId(Long countryId);

	List<Airport> findByCountryIdIn(List<Long> countryId);

	@Query("SELECT a.countryId FROM Airport a GROUP BY a.countryId ORDER BY COUNT(a) DESC")
	    List<Long> countriesWithMostNumberOfAirports();

	@Query("SELECT a.countryId FROM Airport a GROUP BY a.countryId ORDER BY COUNT(a) ASC")
	    List<Long> countriesWithLeastNumberOfAirports();

}
