package com.toparchy.molecule.bi.shipBuilding.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Hxjdjh implements Serializable {

	private static final long serialVersionUID = -1585026867558873015L;
	@Id
	@Column(name = "ppk_varcode")
	private String hxjdjh_id;
	private String cxmc;
	private String gcbh;
	private String gcmc;
	private String xmmc;
	private String xmbh;
	private String xmsx;
	@Temporal(TemporalType.DATE)
	@Column(name = "jhkg1")
	private Date jhkg;
	@Temporal(TemporalType.DATE)
	@Column(name = "jhwg1")
	private Date jhwg;

	public String getHxjdjh_id() {
		return hxjdjh_id;
	}

	public void setHxjdjh_id(String hxjdjh_id) {
		this.hxjdjh_id = hxjdjh_id;
	}

	public String getCxmc() {
		return cxmc;
	}

	public void setCxmc(String cxmc) {
		this.cxmc = cxmc;
	}

	public String getGcbh() {
		return gcbh;
	}

	public void setGcbh(String gcbh) {
		this.gcbh = gcbh;
	}

	public String getGcmc() {
		return gcmc;
	}

	public void setGcmc(String gcmc) {
		this.gcmc = gcmc;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getXmbh() {
		return xmbh;
	}

	public void setXmbh(String xmbh) {
		this.xmbh = xmbh;
	}

	public String getXmsx() {
		return xmsx;
	}

	public void setXmsx(String xmsx) {
		this.xmsx = xmsx;
	}

	public Date getJhkg() {
		return jhkg;
	}

	public void setJhkg(Date jhkg) {
		this.jhkg = jhkg;
	}

	public Date getJhwg() {
		return jhwg;
	}

	public void setJhwg(Date jhwg) {
		this.jhwg = jhwg;
	}

}
