package com.toparchy.molecule.ui;

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
		north.addOption("size", 60);
		north.addOption("spacing_open", 0);
		layoutOptions.setNorthOptions(north);

		LayoutOptions south = new LayoutOptions();
		south.addOption("resizable", false);
		south.addOption("closable", false);
		south.addOption("size", 40);
		south.addOption("spacing_open", 0);
		layoutOptions.setSouthOptions(south);

		LayoutOptions west = new LayoutOptions();
		west.addOption("size", "16%");
		west.addOption("minSize", 100);
		west.addOption("maxSize", 300);
		west.addOption("resizeWhileDragging", true);
		layoutOptions.setWestOptions(west);

		LayoutOptions center = new LayoutOptions();
		center.addOption("spacing_open", 0);
		layoutOptions.setCenterOptions(center);

		LayoutOptions childCenterOptions = new LayoutOptions();
		center.setChildOptions(childCenterOptions);

		LayoutOptions centerNorth = new LayoutOptions();
		centerNorth.addOption("size", "71%");
		centerNorth.addOption("resizable", true);
		centerNorth.addOption("closable", false);
		centerNorth.addOption("resizeWhileDragging", true);
		childCenterOptions.setNorthOptions(centerNorth);

		LayoutOptions centerCenter = new LayoutOptions();
		centerCenter.addOption("size", "29%");
		centerCenter.addOption("resizable", true);
		centerCenter.addOption("closable", true);
		childCenterOptions.setCenterOptions(centerCenter);

	}

	public LayoutOptions getLayoutOptions() {
		return layoutOptions;
	}

}
