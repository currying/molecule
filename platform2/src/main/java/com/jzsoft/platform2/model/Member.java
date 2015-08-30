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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@XmlRootElement
@Table(name = "MEMBER_", uniqueConstraints = @UniqueConstraint(columnNames = "EMAIL_"))
public class Member implements Serializable {

	private static final long serialVersionUID = 5238468096924540131L;

	@Id
	@Column(name = "MEMBER_ID_", length = 50)
	@GeneratedValue(generator = "member-uuid")
	@GenericGenerator(name = "member-uuid", strategy = "uuid")
	private String id;

	@NotNull
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[^0-9]*", message = "名字是中英文，不能包含数字")
	@Column(name = "NAME_")
	private String name;

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
	@Size(min = 10, max = 12)
	@Digits(fraction = 0, integer = 12)
	@Column(name = "PHONE_NUMBER_")
	private String phoneNumber;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "member")
	@JsonIgnore
	private Set<WorkTicket> workTickets;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<WorkTicket> getWorkTickets() {
		return workTickets;
	}

	public void setWorkTickets(Set<WorkTicket> workTickets) {
		this.workTickets = workTickets;
	}

}
