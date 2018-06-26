/**
 * 
 */
package com.assessment.lunatechlabs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.assessment.lunatechlabs.domain.Runway;

/**
 * The repo class which performs operations on Runway entity.
 * 
 * @author Libu
 *
 */
public interface RunwayRepository extends CrudRepository<Runway, Long> {

	List<Runway> findByAirportId(Long airportId);

	List<Runway> findByAirportIdIn(List<Long> airportIds);

	List<Runway> findByCountryId(Long countryId);

	List<Runway> findByCountryIdIn(List<Long> countryIds);

}
