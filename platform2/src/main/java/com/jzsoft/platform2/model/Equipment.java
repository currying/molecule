package com.jzsoft.platform2.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "EQUIPMENT_")
@XmlRootElement
public class Equipment implements Serializable {

	private static final long serialVersionUID = 4475042680666530659L;

	@Id
	@Column(name = "EQUIPMENT_ID_", length = 50)
	@GeneratedValue(generator = "equipment-uuid")
	@GenericGenerator(name = "equipment-uuid", strategy = "uuid")
	private String id;

	@NotNull(message = "请指定设备类型")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EQUIPMENTTYPE_ID_")
	private EquipmentType equipmentType;

	@NotNull(message = "设备名称不能为空")
	@NotEmpty(message = "请输入设备名称")
	@Column(name = "NAME_")
	private String name;

	@Column(name = "SPECIFICATIONS_")
	private String specifications;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "CUSTOMER_ID_")
	private Customer customer;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "PRODUCTIONDATE_")
	private Date productionDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EquipmentType getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(EquipmentType equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

}
