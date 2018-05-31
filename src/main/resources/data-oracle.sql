DROP TABLE IF EXISTS travel_api.airport;

CREATE TABLE travel_api.airport  (
    id VARCHAR(10)  NOT NULL PRIMARY KEY,
    ident VARCHAR(200),
    type VARCHAR(200),
	name VARCHAR(200),
	latitudeDeg BIGINT,
	longitudeDeg BIGINT,
	elevationFt INT,
	continent VARCHAR(200),
	ISOCountry VARCHAR(200),
	ISORegion VARCHAR(200),
	muncipality VARCHAR(20),
	scheduledService VARCHAR(200),
	gpsCode VARCHAR(200),
	iataCode VARCHAR(200),
	localCode VARCHAR(200),
	homeLink VARCHAR(200),
	wikipediaLink VARCHAR(200),
	keywords VARCHAR(200)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS travel_api.country;

CREATE TABLE travel_api.country  (
    id VARCHAR(10)  NOT NULL PRIMARY KEY,
    code VARCHAR(200),
    name VARCHAR(200),
	continent VARCHAR(200),
	wikipediaLink VARCHAR(200),
	keywords VARCHAR(200)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS travel_api.runway;

CREATE TABLE travel_api.runway  (
    id VARCHAR(40)  NOT NULL PRIMARY KEY,
    airport_ref VARCHAR(200),
    airport_ident VARCHAR(200),
	length_ft VARCHAR(200),
	width_ft VARCHAR(200),
	surface VARCHAR(200),
	lighted VARCHAR(200),
	closed VARCHAR(200),
	le_ident VARCHAR(200),
	le_elevation_ft VARCHAR(200),
	le_heading_degT VARCHAR(200),
	le_displaced_threshold_ft VARCHAR(200),
	he_ident VARCHAR(200),
	he_elevation_ft VARCHAR(200),
	he_heading_degT VARCHAR(200),
	he_displaced_threshold_ft VARCHAR(200)
	)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

