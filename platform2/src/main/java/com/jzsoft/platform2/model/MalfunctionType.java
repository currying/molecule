package com.jzsoft.platform2.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "MALFUNCTIONTYPE_")
@XmlRootElement
public class MalfunctionType implements Serializable {

	private static final long serialVersionUID = 8642961168431179071L;

	@Id
	@Column(name = "MALFUNCTIONTYPE_ID_", length = 50)
	@GeneratedValue(generator = "malfunctionType-uuid")
	@GenericGenerator(name = "malfunctionType-uuid", strategy = "uuid")
	private String id;

	@NotNull
	@NotEmpty
	@Column(name = "MALFUNCTIONTYPENAME_")
	private String malfunctionTypeName;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "malfunctionType")
	@JsonIgnore
	private Set<Malfunction> malfunctions;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMalfunctionTypeName() {
		return malfunctionTypeName;
	}

	public void setMalfunctionTypeName(String malfunctionTypeName) {
		this.malfunctionTypeName = malfunctionTypeName;
	}

	public Set<Malfunction> getMalfunctions() {
		return malfunctions;
	}

	public void setMalfunctions(Set<Malfunction> malfunctions) {
		this.malfunctions = malfunctions;
	}

}
