package com.toparchy.molecule.bi.shipBuilding.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Wldzjh implements Serializable {
	private static final long serialVersionUID = 9203028699921652250L;
	@Id
	@Column(name = "ppk_varcode")
	private String wldzjhjh_id;
	private String xh;
	private String postTask;
	private String criticalNode;
	private String frontTask;

	public String getWldzjhjh_id() {
		return wldzjhjh_id;
	}

	public void setWldzjhjh_id(String wldzjhjh_id) {
		this.wldzjhjh_id = wldzjhjh_id;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getPostTask() {
		return postTask;
	}

	public void setPostTask(String postTask) {
		this.postTask = postTask;
	}

	public String getCriticalNode() {
		return criticalNode;
	}

	public void setCriticalNode(String criticalNode) {
		this.criticalNode = criticalNode;
	}

	public String getFrontTask() {
		return frontTask;
	}

	public void setFrontTask(String frontTask) {
		this.frontTask = frontTask;
	}

}
