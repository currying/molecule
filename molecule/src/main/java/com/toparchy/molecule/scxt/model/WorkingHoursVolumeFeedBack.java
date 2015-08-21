package com.toparchy.molecule.scxt.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class WorkingHoursVolumeFeedBack implements Serializable {

	private static final long serialVersionUID = 7507853706368830266L;
	@Id
	private String ppk_varcode;
	private String pgdbh, rybh, rymc, gzmc, gsfl, sdgs, fksj;

	public String getPpk_varcode() {
		return ppk_varcode;
	}

	public void setPpk_varcode(String ppk_varcode) {
		this.ppk_varcode = ppk_varcode;
	}

	public String getPgdbh() {
		return pgdbh;
	}

	public void setPgdbh(String pgdbh) {
		this.pgdbh = pgdbh;
	}

	public String getRybh() {
		return rybh;
	}

	public void setRybh(String rybh) {
		this.rybh = rybh;
	}

	public String getRymc() {
		return rymc;
	}

	public void setRymc(String rymc) {
		this.rymc = rymc;
	}

	public String getGzmc() {
		return gzmc;
	}

	public void setGzmc(String gzmc) {
		this.gzmc = gzmc;
	}

	public String getGsfl() {
		return gsfl;
	}

	public void setGsfl(String gsfl) {
		this.gsfl = gsfl;
	}

	public String getSdgs() {
		return sdgs;
	}

	public void setSdgs(String sdgs) {
		this.sdgs = sdgs;
	}

	public String getFksj() {
		return fksj;
	}

	public void setFksj(String fksj) {
		this.fksj = fksj;
	}

}
