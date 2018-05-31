/**
 * 
 */
package com.assessment.lunatechlabs.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Libu
 *
 */
@Entity
@Table(name="runway")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Runways implements Serializable {
	
	private static final long serialVersionUID = -4087970586697604468L;
	
	@Id
	@Column(name="id")
	private String id;
	
	private String airport_refs;
	
	@Column(name="airport_ident")
	private String airportIdent;
	
	@Column(name="length_ft")
	private String lengthFt;
	
	@Column(name="width_ft")
	private String widthFt;
	
	@Column(name="surface")
	private String surface;
	
	@Column(name="lighted")
	private String lighted;
	
	@Column(name="closed")
	private String closed;
	
	@Column(name="le_ident")
	private String leIdent;
	
	@Column(name="le_elevation_ft")
	private String leElevationFt;
	
	@Column(name="le_heading_degT")
	private String leHeadingDegT;
	
	@Column(name="le_displaced_threshold_ft")
	private String leDisplacedTthresholdFt;
	
	@Column(name="he_ident")
	private String heIdent;
	
	@Column(name="he_elevation_ft")
	private String heElevationFt;
	
	@Column(name="he_heading_degT")
	private String heHeadingDegT;
	
	@Column(name="he_displaced_threshold_ft")
	private String heDisplacedThresholdFt;
	
	@ManyToOne
	@JoinColumn(name="airport_ref")
	private Airport airport;
}
