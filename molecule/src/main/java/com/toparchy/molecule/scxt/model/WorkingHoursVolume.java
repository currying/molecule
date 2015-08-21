package com.toparchy.molecule.scxt.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class WorkingHoursVolume implements Serializable {

	private static final long serialVersionUID = -3992812171938721381L;
	@Id
	private String ppk_varcode;
	private String pgdbh, gcbh, kdrq, sgxm, sglwd, pgnr, sgqy, jhkgsj, jhwgsj, pgzt, kdry, cxmc, pgdms, zymc, jdmc,
			xmhf, zylx, sgbm, sgbz, degs, sgdw, lwlx;

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

	public String getGcbh() {
		return gcbh;
	}

	public void setGcbh(String gcbh) {
		this.gcbh = gcbh;
	}

	public String getKdrq() {
		return kdrq;
	}

	public void setKdrq(String kdrq) {
		this.kdrq = kdrq;
	}

	public String getSgxm() {
		return sgxm;
	}

	public void setSgxm(String sgxm) {
		this.sgxm = sgxm;
	}

	public String getSglwd() {
		return sglwd;
	}

	public void setSglwd(String sglwd) {
		this.sglwd = sglwd;
	}

	public String getPgnr() {
		return pgnr;
	}

	public void setPgnr(String pgnr) {
		this.pgnr = pgnr;
	}

	public String getSgqy() {
		return sgqy;
	}

	public void setSgqy(String sgqy) {
		this.sgqy = sgqy;
	}

	public String getJhkgsj() {
		return jhkgsj;
	}

	public void setJhkgsj(String jhkgsj) {
		this.jhkgsj = jhkgsj;
	}

	public String getJhwgsj() {
		return jhwgsj;
	}

	public void setJhwgsj(String jhwgsj) {
		this.jhwgsj = jhwgsj;
	}

	public String getPgzt() {
		return pgzt;
	}

	public void setPgzt(String pgzt) {
		this.pgzt = pgzt;
	}

	public String getKdry() {
		return kdry;
	}

	public void setKdry(String kdry) {
		this.kdry = kdry;
	}

	public String getCxmc() {
		return cxmc;
	}

	public void setCxmc(String cxmc) {
		this.cxmc = cxmc;
	}

	public String getPgdms() {
		return pgdms;
	}

	public void setPgdms(String pgdms) {
		this.pgdms = pgdms;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getJdmc() {
		return jdmc;
	}

	public void setJdmc(String jdmc) {
		this.jdmc = jdmc;
	}

	public String getXmhf() {
		return xmhf;
	}

	public void setXmhf(String xmhf) {
		this.xmhf = xmhf;
	}

	public String getZylx() {
		return zylx;
	}

	public void setZylx(String zylx) {
		this.zylx = zylx;
	}

	public String getSgbm() {
		return sgbm;
	}

	public void setSgbm(String sgbm) {
		this.sgbm = sgbm;
	}

	public String getSgbz() {
		return sgbz;
	}

	public void setSgbz(String sgbz) {
		this.sgbz = sgbz;
	}

	public String getDegs() {
		return degs;
	}

	public void setDegs(String degs) {
		this.degs = degs;
	}

	public String getSgdw() {
		return sgdw;
	}

	public void setSgdw(String sgdw) {
		this.sgdw = sgdw;
	}

	public String getLwlx() {
		return lwlx;
	}

	public void setLwlx(String lwlx) {
		this.lwlx = lwlx;
	}

}
