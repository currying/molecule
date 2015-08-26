package com.toparchy.molecule.wzxt.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class MaterialStorage implements Serializable {
	private static final long serialVersionUID = -163996461818142400L;
	@Id
	private String wzrkd_id;
	private String khmc, khbh, kdrq, ldbh, cgy, htbh, bmbh, bmmc, ckbh, ckmc, rklx, gcbh, gcmc, rkly, shry, shrq, wzbh,
			wzmc, wzms, rksl, sssl, jldw, wdysm, shbz, bz;

	public String getKhmc() {
		return khmc;
	}

	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}

	public String getKhbh() {
		return khbh;
	}

	public void setKhbh(String khbh) {
		this.khbh = khbh;
	}

	public String getKdrq() {
		return kdrq;
	}

	public void setKdrq(String kdrq) {
		this.kdrq = kdrq;
	}

	public String getLdbh() {
		return ldbh;
	}

	public void setLdbh(String ldbh) {
		this.ldbh = ldbh;
	}

	public String getCgy() {
		return cgy;
	}

	public void setCgy(String cgy) {
		this.cgy = cgy;
	}

	public String getHtbh() {
		return htbh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}

	public String getBmbh() {
		return bmbh;
	}

	public void setBmbh(String bmbh) {
		this.bmbh = bmbh;
	}

	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	public String getCkbh() {
		return ckbh;
	}

	public void setCkbh(String ckbh) {
		this.ckbh = ckbh;
	}

	public String getCkmc() {
		return ckmc;
	}

	public void setCkmc(String ckmc) {
		this.ckmc = ckmc;
	}

	public String getRklx() {
		return rklx;
	}

	public void setRklx(String rklx) {
		this.rklx = rklx;
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

	public String getRkly() {
		return rkly;
	}

	public void setRkly(String rkly) {
		this.rkly = rkly;
	}

	public String getShry() {
		return shry;
	}

	public void setShry(String shry) {
		this.shry = shry;
	}

	public String getShrq() {
		return shrq;
	}

	public void setShrq(String shrq) {
		this.shrq = shrq;
	}

	public String getWzbh() {
		return wzbh;
	}

	public void setWzbh(String wzbh) {
		this.wzbh = wzbh;
	}

	public String getWzmc() {
		return wzmc;
	}

	public void setWzmc(String wzmc) {
		this.wzmc = wzmc;
	}

	public String getWzms() {
		return wzms;
	}

	public void setWzms(String wzms) {
		this.wzms = wzms;
	}

	public String getRksl() {
		return rksl;
	}

	public void setRksl(String rksl) {
		this.rksl = rksl;
	}

	public String getSssl() {
		return sssl;
	}

	public void setSssl(String sssl) {
		this.sssl = sssl;
	}

	public String getJldw() {
		return jldw;
	}

	public void setJldw(String jldw) {
		this.jldw = jldw;
	}

	public String getWdysm() {
		return wdysm;
	}

	public void setWdysm(String wdysm) {
		this.wdysm = wdysm;
	}

	public String getShbz() {
		return shbz;
	}

	public void setShbz(String shbz) {
		this.shbz = shbz;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getWzrkd_id() {
		return wzrkd_id;
	}

	public void setWzrkd_id(String wzrkd_id) {
		this.wzrkd_id = wzrkd_id;
	}

}
