package com.toparchy.molecule.ui;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;

import org.primefaces.extensions.model.layout.LayoutOptions;

@Model
public class LoginPageController implements Serializable {

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
		south.addOption("size", 100);
		south.addOption("spacing_open", 0);
		layoutOptions.setSouthOptions(south);

		LayoutOptions west = new LayoutOptions();
		west.addOption("size", "32%");
		west.addOption("resizable", false);
		west.addOption("closable", false);
		west.addOption("spacing_open", 0);
		layoutOptions.setWestOptions(west);

		LayoutOptions east = new LayoutOptions();
		east.addOption("size", "32%");
		east.addOption("resizable", false);
		east.addOption("closable", false);
		east.addOption("spacing_open", 0);
		layoutOptions.setEastOptions(east);

		LayoutOptions center = new LayoutOptions();
		center.addOption("spacing_open", 0);
		layoutOptions.setCenterOptions(center);

		LayoutOptions childCenterOptions = new LayoutOptions();
		center.setChildOptions(childCenterOptions);

		LayoutOptions centerCenter = new LayoutOptions();
		centerCenter.addOption("resizable", false);
		centerCenter.addOption("closable", false);
		centerCenter.addOption("spacing_open", 0);
		childCenterOptions.setNorthOptions(centerCenter);

	}

	public LayoutOptions getLayoutOptions() {
		return layoutOptions;
	}

}
