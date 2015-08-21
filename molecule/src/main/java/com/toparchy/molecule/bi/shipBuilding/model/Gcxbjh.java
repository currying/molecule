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
public class Gcxbjh implements Serializable {
	private static final long serialVersionUID = -514877909421120801L;
	@Id
	@Column(name = "ppk_varcode")
	private String gcxbjh_id;
	private String gcbh;
	@Temporal(TemporalType.DATE)
	@Column(name = "jhkgsj1")
	private Date jhkg;
	@Temporal(TemporalType.DATE)
	@Column(name = "sjkgsj1")
	private Date sjkg;

	@Temporal(TemporalType.DATE)
	@Column(name = "jhkgsj2")
	private Date jhjw;
	@Temporal(TemporalType.DATE)
	@Column(name = "sjkgsj2")
	private Date sjjw;

	@Temporal(TemporalType.DATE)
	@Column(name = "jhkgsj3")
	private Date jhcw;
	@Temporal(TemporalType.DATE)
	@Column(name = "sjkgsj3")
	private Date sjcw;

	@Temporal(TemporalType.DATE)
	@Column(name = "jhkgsj4")
	private Date jhsh;
	@Temporal(TemporalType.DATE)
	@Column(name = "sjkgsj4")
	private Date sjsh;

	@Temporal(TemporalType.DATE)
	@Column(name = "jhkgsj5")
	private Date jhjc;
	@Temporal(TemporalType.DATE)
	@Column(name = "sjkgsj5")
	private Date sjjc;

	public Gcxbjh() {
	}

	public String getGcxbjh_id() {
		return gcxbjh_id;
	}

	public void setGcxbjh_id(String gcxbjh_id) {
		this.gcxbjh_id = gcxbjh_id;
	}

	public String getGcbh() {
		return gcbh;
	}

	public void setGcbh(String gcbh) {
		this.gcbh = gcbh;
	}

	public Date getJhkg() {
		return jhkg;
	}

	public void setJhkg(Date jhkg) {
		this.jhkg = jhkg;
	}

	public Date getSjkg() {
		return sjkg;
	}

	public void setSjkg(Date sjkg) {
		this.sjkg = sjkg;
	}

	public Date getJhjw() {
		return jhjw;
	}

	public void setJhjw(Date jhjw) {
		this.jhjw = jhjw;
	}

	public Date getSjjw() {
		return sjjw;
	}

	public void setSjjw(Date sjjw) {
		this.sjjw = sjjw;
	}

	public Date getJhcw() {
		return jhcw;
	}

	public void setJhcw(Date jhcw) {
		this.jhcw = jhcw;
	}

	public Date getSjcw() {
		return sjcw;
	}

	public void setSjcw(Date sjcw) {
		this.sjcw = sjcw;
	}

	public Date getJhsh() {
		return jhsh;
	}

	public void setJhsh(Date jhsh) {
		this.jhsh = jhsh;
	}

	public Date getSjsh() {
		return sjsh;
	}

	public void setSjsh(Date sjsh) {
		this.sjsh = sjsh;
	}

	public Date getJhjc() {
		return jhjc;
	}

	public void setJhjc(Date jhjc) {
		this.jhjc = jhjc;
	}

	public Date getSjjc() {
		return sjjc;
	}

	public void setSjjc(Date sjjc) {
		this.sjjc = sjjc;
	}

}
