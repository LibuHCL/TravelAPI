package com.assessment.lunatechlabs;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.assessment.lunatechlabs.domain.Airport;
import com.assessment.lunatechlabs.domain.Country;
import com.assessment.lunatechlabs.domain.Runway;
import com.assessment.lunatechlabs.repository.AirportRepository;
import com.assessment.lunatechlabs.repository.CountryRepository;
import com.assessment.lunatechlabs.repository.RunwayRepository;
import com.assessment.lunatechlabs.utilities.CsvUtils;

@SpringBootApplication
public class TravelApiApplication {

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	AirportRepository airportRepository;

	@Autowired
	RunwayRepository runwayRepository;

	@Bean
	@Profile("!test")
	public CommandLineRunner bootstrap(ApplicationContext ctx) {
		return args -> {
			if (!countryRepository.findAll().iterator().hasNext()) {
				saveCountries();
				saveAirports();
				saveRunways();
			}
		};
	}

	void saveCountries() throws IOException {
		CsvUtils.readCsv("countries.csv").parallelStream().forEach(row -> {
			Country country = Country.from(row);

			countryRepository.save(country);
		});
	}

	void saveAirports() throws IOException {
		CsvUtils.readCsv("airports.csv").parallelStream().forEach(row -> {
			Airport airport = Airport.from(row,
					country -> countryRepository.findByLowerCaseCode(country.toLowerCase()).getId());
			airportRepository.save(airport);
		});
	}

	void saveRunways() throws IOException {
		CsvUtils.readCsv("runways.csv").parallelStream().forEach(row -> {
			Runway runway = Runway.from(row, airportId -> airportRepository.findOne(airportId).getCountryId());

			runwayRepository.save(runway);
		});
	}

	public static void main(String[] args) {
		SpringApplication.run(TravelApiApplication.class, args);
	}

}
