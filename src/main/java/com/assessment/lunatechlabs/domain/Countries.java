/**
 * 
 */
package com.assessment.lunatechlabs.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 

/**
 * @author Libu
 *
 */
@Entity
@Table(name="country")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Countries implements Serializable {

	private static final long serialVersionUID = -4087970586697604467L;

	@Column(name="id")
	private String id;
	
	@Id
	@Column(name="code")
	private String code;

	@Column(name="name")
	private String name;

	@Column(name="continent")
	private String continent;

	@Column(name="wikipediaLink")
	private String wikipediaLink;

	@Column(name="keywords")
	private String keywords;
	
	@OneToMany(mappedBy="countries")
	private Set<Airport> airport;
}
