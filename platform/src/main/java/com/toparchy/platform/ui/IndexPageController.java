package com.toparchy.platform.ui;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;

import org.primefaces.extensions.model.layout.LayoutOptions;

@Model
public class IndexPageController implements Serializable {

	private static final long serialVersionUID = -4713453022004741813L;
	private LayoutOptions layoutOptions;

	@PostConstruct
	protected void initialize() {
		layoutOptions = new LayoutOptions();
		LayoutOptions panes = new LayoutOptions();
		panes.addOption("slidable", "false");
		layoutOptions.setPanesOptions(panes);

		LayoutOptions north = new LayoutOptions();
		north.addOption("resizable", false);
		north.addOption("closable", false);
		north.addOption("size", 80);
		north.addOption("spacing_open", 0);
		layoutOptions.setNorthOptions(north);

		LayoutOptions south = new LayoutOptions();
		south.addOption("resizable", false);
		south.addOption("closable", false);
		south.addOption("size", 80);
		south.addOption("spacing_open", 0);
		layoutOptions.setSouthOptions(south);

		LayoutOptions center = new LayoutOptions();
		center.addOption("resizable", false);
		center.addOption("closable", false);
		center.addOption("spacing_open", 0);
		layoutOptions.setCenterOptions(center);

	}

	public LayoutOptions getLayoutOptions() {
		return layoutOptions;
	}

}
