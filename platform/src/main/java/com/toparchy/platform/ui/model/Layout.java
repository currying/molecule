package com.toparchy.platform.ui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@XmlRootElement
@Table
public class Layout implements Serializable {
	private static final long serialVersionUID = 5520200625299749810L;

	@Id
	@Column(name = "ID_", length = 50)
	@GeneratedValue(generator = "layout-uuid")
	@GenericGenerator(name = "layout-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	private boolean slidable;
	@OneToMany(fetch = FetchType.EAGER)
	private List<PositionLayout> positionLayouts = new ArrayList<PositionLayout>();
	private String north;
	private String south;
	private String west;
	private String east;
	private String center;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isSlidable() {
		return slidable;
	}

	public void setSlidable(boolean slidable) {
		this.slidable = slidable;
	}

	public List<PositionLayout> getPositionLayouts() {
		return positionLayouts;
	}

	public void setPositionLayouts(List<PositionLayout> positionLayouts) {
		this.positionLayouts = positionLayouts;
	}

	public void addPositionLayout(PositionLayout positionLayout) {
		positionLayouts.add(positionLayout);
	}

	public String getNorth() {
		return north;
	}

	public void setNorth(String north) {
		this.north = north;
	}

	public String getSouth() {
		return south;
	}

	public void setSouth(String south) {
		this.south = south;
	}

	public String getWest() {
		return west;
	}

	public void setWest(String west) {
		this.west = west;
	}

	public String getEast() {
		return east;
	}

	public void setEast(String east) {
		this.east = east;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

}
