/**
 * 
 */
package com.assessment.lunatechlabs.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.assessment.lunatechlabs.domain.Airport;
import com.assessment.lunatechlabs.domain.Countries;
import com.assessment.lunatechlabs.domain.Runways;
import com.assessment.lunatechlabs.processors.CountryItemProcessor;
import com.assessment.lunatechlabs.processors.RunwayItemProcessor;
import com.assessment.lunatechlabs.processors.AirportItemProcessor;

/**
 * @author Libu
 *
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Value("${spring.datasource.driver-class-name}")
	private String driverClass;

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource source;

	@Bean
	public FlatFileItemReader<Countries> reader(){
		FlatFileItemReader<Countries> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("./countries.csv"));
		reader.setLineMapper(new DefaultLineMapper<Countries>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setNames(new String[] {"id","code" ,"name" ,"continent","wikipediaLink","keywords"});
			}});
			setFieldSetMapper(new BeanWrapperFieldSetMapper<Countries>() {{
				setTargetType(Countries.class);
			}});
		}});
		return reader;

	}

	@Bean
	public CountryItemProcessor processor(){
		return new CountryItemProcessor();
	}



	@Bean
	public JdbcBatchItemWriter<Countries> writer(){
		JdbcBatchItemWriter<Countries> writer = new JdbcBatchItemWriter<Countries>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Countries>());
		writer.setSql("INSERT INTO country(id,code,name,continent,wikipediaLink,keywords) VALUES (:id,:code,:name,:continent,:wikipediaLink,:keywords)");
		writer.setDataSource(source);

		return writer;
	}


	@Bean
	public FlatFileItemReader<Airport> airportReader(){
		FlatFileItemReader<Airport> airportReader = new FlatFileItemReader<>();
		airportReader.setResource(new ClassPathResource("./airports.csv"));
		airportReader.setLineMapper(new DefaultLineMapper<Airport>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setNames(new String[] {"id","ident" ,"type", "name" , "elevation_ft", "continent", 
						"iso_country", "iso_region", "municipality", "scheduled_service", "gps_code", "iata_code", "local_code", "home_link",
						"wikipedia_link", "keywords"});
			}});
			setFieldSetMapper(new BeanWrapperFieldSetMapper<Airport>() {{
				setTargetType(Airport.class);
			}});
		}});
		return airportReader;

	}


	@Bean
	public AirportItemProcessor airportProcessor(){
		return new AirportItemProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Airport> airportWriter(){
		JdbcBatchItemWriter<Airport> airportWriter = new JdbcBatchItemWriter<Airport>();
		airportWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Airport>());
		airportWriter.setSql("INSERT INTO airport(id,ident,type,name,elevationFt,continent,ISOCountry,ISORegion,muncipality,scheduledService,gpsCode,iataCode,localCode,homeLink,wikipediaLink,keywords) "
				+ "VALUES (:id,:ident,:type,:name,:elevationFt,:continent,:isoCountrys,:ISORegion,:muncipality,:scheduledService,:gpsCode,:iataCode,:localCode,:homeLink,:wikipediaLink,:keywords)");
		airportWriter.setDataSource(source);

		return airportWriter;
	}
	
	@Bean
	public FlatFileItemReader<Runways> runwayReader(){
		FlatFileItemReader<Runways> runwayReader = new FlatFileItemReader<>();
		runwayReader.setResource(new ClassPathResource("./runways.csv"));
		runwayReader.setLineMapper(new DefaultLineMapper<Runways>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setNames(new String[] {"id","airport_ref" ,"airport_ident" ,"length_ft","width_ft","surface","lighted","closed",
						"le_ident","le_elevation_ft","le_heading_degT","le_displaced_threshold_ft","he_ident","he_elevation_ft",
						"he_heading_degT","he_displaced_threshold_ft"});
			}});
			setFieldSetMapper(new BeanWrapperFieldSetMapper<Runways>() {{
				setTargetType(Runways.class);
			}});
		}});
		return runwayReader;

	}
	
	@Bean
	public RunwayItemProcessor runwayProcessor(){
		return new RunwayItemProcessor();
	}
	
	@Bean
	public JdbcBatchItemWriter<Runways> runwayWriter(){
		JdbcBatchItemWriter<Runways> runwayWriter = new JdbcBatchItemWriter<Runways>();
		runwayWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Runways>());
		runwayWriter.setSql("INSERT INTO runway(id,airport_ref,airport_ident,length_ft,width_ft,surface,lighted,closed,le_ident,le_elevation_ft,le_heading_degT,le_displaced_threshold_ft,he_ident,he_elevation_ft,he_heading_degT,he_displaced_threshold_ft) "
				+ "VALUES (:id,:airport_refs,:airportIdent,:lengthFt,:widthFt,:surface,:lighted,:closed,:leIdent,:leElevationFt,:leHeadingDegT,:leDisplacedTthresholdFt,:heIdent,:heElevationFt,:heHeadingDegT,:heDisplacedThresholdFt)");
		runwayWriter.setDataSource(source);

		return runwayWriter;
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Countries, Countries> chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2").<Airport, Airport> chunk(10)
				.reader(airportReader())
				.processor(airportProcessor())
				.writer(airportWriter())
				.build();
	}
	
	@Bean
	public Step step3() {
		return stepBuilderFactory.get("step3").<Runways, Runways> chunk(10)
				.reader(runwayReader())
				.processor(runwayProcessor())
				.writer(runwayWriter())
				.build();
	}


	@Bean
	public Job importUserJob() {
		return jobBuilderFactory.get("importTravelJob")
				.incrementer(new RunIdIncrementer())
				.start(step1())
				.next(step2())
				.next(step3())
				.build();
	}



}
