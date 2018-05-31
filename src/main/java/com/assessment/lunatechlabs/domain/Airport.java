/**
 * 
 */
package com.assessment.lunatechlabs.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Libu
 *
 */
@Entity
@Table(name="airport")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airport implements Serializable {
	
	private static final long serialVersionUID = -4087970586697604466L;
	
	@Id
	@Column(name="id")
	private String id;
	
	private String isoCountrys;
	
	@Column(name="ident")
	private String ident;
	
	@Column(name="type")
	private String type;
	
	@Column(name="name")
	private String name;
	
	@Column(name="elevationFt")
	private String elevationFt;
	
	@Column(name="continent")
	private String continent;
	
	@Column(name="ISORegion")
	private String ISORegion;
	
	@Column(name="muncipality")
	private String muncipality;
	
	@Column(name="scheduledService")
	private String scheduledService;
	
	@Column(name="gpsCode")
	private String gpsCode;
	
	@Column(name="iataCode")
	private String iataCode;
	
	@Column(name="localCode")
	private String localCode;
	
	@Column(name="homeLink")
	private String homeLink;
	
	@Column(name="wikipediaLink")
	private String wikipediaLink;
	
	@Column(name="keywords")
	private String keywords;
	
	@ManyToOne
	@JoinColumn(name="ISOCountry")
	private Countries countries;
	
	@OneToMany(mappedBy="airport")
	private Set<Runways> runway;
}
