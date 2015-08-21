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
public class Zhdrcjh implements Serializable {

	private static final long serialVersionUID = 4320144603308615988L;
	@Id
	@Column(name = "ppk_varcode")
	private String zhrch_id;
	private String cxmc;
	private String gcbh;
	private String gcmc;
	private String xmbh;
	private String xmmc;
	private String xmlb;
	@Temporal(TemporalType.DATE)
	private Date jhkg;
	@Temporal(TemporalType.DATE)
	private Date jhwg;

	public String getZhrch_id() {
		return zhrch_id;
	}

	public void setZhrch_id(String zhrch_id) {
		this.zhrch_id = zhrch_id;
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

	public String getXmbh() {
		return xmbh;
	}

	public void setXmbh(String xmbh) {
		this.xmbh = xmbh;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getXmlb() {
		return xmlb;
	}

	public void setXmlb(String xmlb) {
		this.xmlb = xmlb;
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
