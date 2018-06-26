/**
 * 
 */
package com.assessment.lunatechlabs.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.assessment.lunatechlabs.repository.CountryRepository;

/**
 * @author Libu
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class QueryControllerTest {

	@MockBean
	private CountryRepository countryRepository;

	@Test
	public void dummy() {

	}

}
