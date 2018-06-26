/**
 * 
 */
package com.assessment.lunatechlabs.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.assessment.lunatechlabs.assessment.services.impl.CountryService;
import com.assessment.lunatechlabs.domain.Country;
import com.assessment.lunatechlabs.repository.CountryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * @author Libu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryTest {

	@MockBean
	private CountryRepository countryRepository;

	@Autowired
	private CountryService countryService;

	private static Country india() {
		return Country.builder().id(1).code("IN").name("India").build();
	}

	@Test
	public void testFindCountry() throws Exception {
		given(countryRepository.findByLowerCaseCode("in")).willReturn(india());
		given(countryRepository.findByLowerCaseName("india")).willReturn(india());
		assertThat(countryService.findCountry("IN")).contains(india());
		assertThat(countryService.findCountry("India")).contains(india());

		given(countryRepository.findByLowerCaseCode("netherlands")).willReturn(null);
		given(countryRepository.findByLowerCaseName("nl")).willReturn(null);
		assertThat(countryService.findCountry("NL")).isEmpty();
		assertThat(countryService.findCountry("Netherlands")).isEmpty();
	}

}
