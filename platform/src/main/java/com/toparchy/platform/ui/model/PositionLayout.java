package com.toparchy.platform.ui.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@XmlRootElement
@Table
public class PositionLayout implements Serializable {

	private static final long serialVersionUID = -905298900053708794L;
	@Id
	@Column(name = "ID_", length = 50)
	@GeneratedValue(generator = "positionLayout-uuid")
	@GenericGenerator(name = "positionLayout-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	private String size;
	private int minSize = 0;
	private int maxSize = 0;
	private int minHeight = 0;
	private int maxHeight = 0;
	private int minWidth = 0;
	private int maxWidth = 0;
	private int spacingOpen = 0;
	private int spacingClosed = 0;
	@Column(name = "POSITION_", nullable = false)
	private String positionName;
	private String rendered;
	private String head;
	private String headSrc;
	private String src;
	private boolean resizable = true;
	private boolean closable = true;
	private boolean initClosed = false;
	private boolean initHidden = false;
	@ManyToOne
	@JoinColumn(name = "LAYOUT_ID_")
	private Layout layout;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isResizable() {
		return resizable;
	}

	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}

	public boolean isClosable() {
		return closable;
	}

	public void setClosable(boolean closable) {
		this.closable = closable;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getMinSize() {
		return minSize;
	}

	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public int getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	public int getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(int minWidth) {
		this.minWidth = minWidth;
	}

	public int getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}

	public int getSpacingOpen() {
		return spacingOpen;
	}

	public void setSpacingOpen(int spacingOpen) {
		this.spacingOpen = spacingOpen;
	}

	public int getSpacingClosed() {
		return spacingClosed;
	}

	public void setSpacingClosed(int spacingClosed) {
		this.spacingClosed = spacingClosed;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public String getRendered() {
		return rendered;
	}

	public void setRendered(String rendered) {
		this.rendered = rendered;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public boolean isInitClosed() {
		return initClosed;
	}

	public void setInitClosed(boolean initClosed) {
		this.initClosed = initClosed;
	}

	public boolean isInitHidden() {
		return initHidden;
	}

	public void setInitHidden(boolean initHidden) {
		this.initHidden = initHidden;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getHeadSrc() {
		return headSrc;
	}

	public void setHeadSrc(String headSrc) {
		this.headSrc = headSrc;
	}

}
