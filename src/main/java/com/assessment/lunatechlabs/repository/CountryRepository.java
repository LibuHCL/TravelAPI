/**
 * 
 */
package com.assessment.lunatechlabs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.assessment.lunatechlabs.domain.Country;

/**
 * The repo class which performs operations on Country object.
 * 
 * @author Libu
 *
 */
public interface CountryRepository extends CrudRepository<Country, Long> {

	List<Country> findByLowerCaseNameStartingWith(String name);

	Country findByLowerCaseName(String name);

	Country findByLowerCaseCode(String code);

	List<Country> findByIdIn(List<Long> countryIds);

}
