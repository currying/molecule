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
public class Xxrcjh implements Serializable {

	private static final long serialVersionUID = -7289571113877554335L;
	@Id
	@Column(name = "ppk_varcode")
	private String xxrcjh_id;
	private String gcbh;
	private String gcmc;
	private String sgxm;
	private String xmhf;
	private String jkxm;
	private String glpg;
	private String gllx;
	@Temporal(TemporalType.DATE)
	@Column(name = "sjsj")
	private Date sjsj;
	private String kbys;

	public String getXxrcjh_id() {
		return xxrcjh_id;
	}

	public void setXxrcjh_id(String xxrcjh_id) {
		this.xxrcjh_id = xxrcjh_id;
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

	public String getSgxm() {
		return sgxm;
	}

	public void setSgxm(String sgxm) {
		this.sgxm = sgxm;
	}

	public String getXmhf() {
		return xmhf;
	}

	public void setXmhf(String xmhf) {
		this.xmhf = xmhf;
	}

	public String getJkxm() {
		return jkxm;
	}

	public void setJkxm(String jkxm) {
		this.jkxm = jkxm;
	}

	public String getGlpg() {
		return glpg;
	}

	public void setGlpg(String glpg) {
		this.glpg = glpg;
	}

	public String getGllx() {
		return gllx;
	}

	public void setGllx(String gllx) {
		this.gllx = gllx;
	}

	public Date getSjsj() {
		return sjsj;
	}

	public void setSjsj(Date sjsj) {
		this.sjsj = sjsj;
	}

	public String getKbys() {
		return kbys;
	}

	public void setKbys(String kbys) {
		this.kbys = kbys;
	}

}
