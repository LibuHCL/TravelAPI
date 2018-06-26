/**
 * 
 */
package com.assessment.lunatechlabs.domain;

import java.io.Serializable;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.assessment.lunatechlabs.utilities.CsvUtils.l;
import static com.assessment.lunatechlabs.utilities.CsvUtils.s;
import com.assessment.lunatechlabs.domain.Country;

/**
 * @author Libu
 *
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "countryId", "id", "name" })
@ToString(exclude = { "country", "runways" })
public class Airport implements Serializable {

	private static final long serialVersionUID = -3832055835941264254L;

	@Id
	@Column(name = "airportId")
	private long id;

	@NonNull
	private String ident;

	@NonNull
	private String name;

	private String type;
	private String latitudeDeg;
	private String longitudeDeg;
	private String elevationFt;
	private String continent;
	private String isoCountry;
	private String isoRegion;
	private String municipality;
	private String scheduledService;
	private String gpsCode;
	private String iataCode;
	private String localCode;
	private String homeLink;
	private String wikipediaLink;
	private String keywords;

	@NonNull
	private Long countryId;

	@Transient
	private transient Country country;

	@Singular
	@Transient
	private List<Runway> runways;

	public static Airport from(Map<String, String> values, Function<String, Long> countryProvider) {
		return builder().id(l(values, "id")).ident(s(values, "ident")).type(s(values, "type")).name(s(values, "name"))
				.latitudeDeg(s(values, "latitude_deg")).longitudeDeg(s(values, "longitude_deg"))
				.elevationFt(s(values, "elevation_ft")).continent(s(values, "continent"))
				.isoCountry(s(values, "iso_country")).countryId(countryProvider.apply(s(values, "iso_country")))
				.isoRegion(s(values, "iso_region")).municipality(s(values, "municipality"))
				.scheduledService(s(values, "scheduled_service")).gpsCode(s(values, "gps_code"))
				.iataCode(s(values, "iata_code")).localCode(s(values, "local_code")).homeLink(s(values, "home_link"))
				.wikipediaLink(s(values, "wikipedia_link")).keywords(s(values, "keywords")).build();
	}

}
