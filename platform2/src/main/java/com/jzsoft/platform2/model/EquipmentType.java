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
@Table(name = "EQUIPMENTTYPE_")
@XmlRootElement
public class EquipmentType implements Serializable {

	private static final long serialVersionUID = 7126264295962381851L;

	@Id
	@Column(name = "EQUIPMENTTYPE_ID_", length = 50)
	@GeneratedValue(generator = "equipmentType-uuid")
	@GenericGenerator(name = "equipmentType-uuid", strategy = "uuid")
	private String id;

	@NotNull(message = "设备类型名称不能为空")
	@NotEmpty(message = "请输入设备类型名称")
	@Column(name = "NAME_")
	private String name;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "equipmentType")
	@JsonIgnore
	private Set<Equipment> equipments;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(Set<Equipment> equipments) {
		this.equipments = equipments;
	}

}
