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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CUSTOMER_", uniqueConstraints = @UniqueConstraint(columnNames = {
		"CUSTOMERNAME_", "PHONE_NUMBER_" }))
@XmlRootElement
public class Customer implements Serializable {
	private static final long serialVersionUID = 214800817203529556L;

	@Id
	@Column(name = "CUSTOMER_ID_", length = 50)
	@GeneratedValue(generator = "customer-uuid")
	@GenericGenerator(name = "customer-uuid", strategy = "uuid")
	private String id;

	@NotNull
	@Column(name = "CUSTOMERNAME_")
	private String customerName;

	@Size(min = 5, max = 20)
	@Column(name = "LOGINNAME_")
	private String loginName;

	@Size(min = 8, max = 24)
	@Column(name = "PASSWORD_")
	private String password;

	@NotNull
	@NotEmpty
	@Column(name = "EMAIL_")
	@Email
	private String email;

	@NotNull
	@Column(name = "ADDRESS_")
	private String address;

	@NotNull
	@Column(name = "CONTACT_")
	private String contact;

	@NotNull
	@Size(min = 10, max = 12)
	@Digits(fraction = 0, integer = 12)
	@Column(name = "PHONE_NUMBER_")
	private String phoneNumber;

	@Column(name = "TIME_")
	private Long time;

	@Column(name = "RATINGS_")
	private int ratings;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "customer")
	@JsonIgnore
	private Set<Malfunction> malfunctions;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "customer")
	@JsonIgnore
	private Set<Equipment> equipments;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	public Set<Malfunction> getMalfunctions() {
		return malfunctions;
	}

	public void setMalfunctions(Set<Malfunction> malfunctions) {
		this.malfunctions = malfunctions;
	}

}
