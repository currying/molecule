package com.toparchy.platform.ui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.extensions.model.layout.LayoutOptions;

import com.toparchy.platform.security.model.User;
import com.toparchy.platform.ui.ejb.LayoutEJB;
import com.toparchy.platform.ui.model.Layout;
import com.toparchy.platform.ui.model.PositionLayout;

@Named
@ViewScoped
public class CustomContentLayoutController implements Serializable {

	private static final long serialVersionUID = 2093903038461066367L;
	private LayoutOptions layoutOptions;
	@Inject
	@Named("currentUser")
	private User currentUser;
	@EJB
	private LayoutEJB layoutEJB;
	private Map<String, PositionLayout> pLayout = new HashMap<String, PositionLayout>();
	private Layout layout;
	private PositionLayout northLayout;
	private PositionLayout north_northLayout;
	private PositionLayout north_southLayout;
	private PositionLayout north_westLayout;
	private PositionLayout north_eastLayout;
	private PositionLayout north_centerLayout;
	private PositionLayout southLayout;
	private PositionLayout south_northLayout;
	private PositionLayout south_southLayout;
	private PositionLayout south_westLayout;
	private PositionLayout south_eastLayout;
	private PositionLayout south_centerLayout;
	private PositionLayout westLayout;
	private PositionLayout west_northLayout;
	private PositionLayout west_southLayout;
	private PositionLayout west_westLayout;
	private PositionLayout west_eastLayout;
	private PositionLayout west_centerLayout;
	private PositionLayout eastLayout;
	private PositionLayout east_northLayout;
	private PositionLayout east_southLayout;
	private PositionLayout east_westLayout;
	private PositionLayout east_eastLayout;
	private PositionLayout east_centerLayout;
	private PositionLayout centerLayout;
	private PositionLayout center_northLayout;
	private PositionLayout center_southLayout;
	private PositionLayout center_westLayout;
	private PositionLayout center_eastLayout;
	private PositionLayout center_centerLayout;

	@PostConstruct
	protected void initialize() {
		layout = layoutEJB.getLayout(currentUser.getPerson().getLayout()
				.getId());
		createPositionLayout(layout);
		northLayout = pLayout.get("north");
		north_northLayout = pLayout.get("north_north");
		north_southLayout = pLayout.get("north_south");
		north_westLayout = pLayout.get("north_west");
		north_eastLayout = pLayout.get("north_east");
		north_centerLayout = pLayout.get("north_center");
		southLayout = pLayout.get("south");
		south_northLayout = pLayout.get("south_north");
		south_southLayout = pLayout.get("south_south");
		south_westLayout = pLayout.get("south_west");
		south_eastLayout = pLayout.get("south_east");
		south_centerLayout = pLayout.get("south_center");
		westLayout = pLayout.get("west");
		west_northLayout = pLayout.get("west_north");
		west_southLayout = pLayout.get("west_south");
		west_westLayout = pLayout.get("west_west");
		west_eastLayout = pLayout.get("west_east");
		west_centerLayout = pLayout.get("west_center");
		eastLayout = pLayout.get("east");
		east_northLayout = pLayout.get("east_north");
		east_southLayout = pLayout.get("east_south");
		east_westLayout = pLayout.get("east_west");
		east_eastLayout = pLayout.get("east_east");
		east_centerLayout = pLayout.get("east_center");
		centerLayout = pLayout.get("center");
		center_northLayout = pLayout.get("center_north");
		center_southLayout = pLayout.get("center_south");
		center_westLayout = pLayout.get("center_west");
		center_eastLayout = pLayout.get("center_east");
		center_centerLayout = pLayout.get("center_center");

		layoutOptions = new LayoutOptions();
		// options for all panes
		LayoutOptions panes = new LayoutOptions();
		panes.addOption("slidable", layout.isSlidable());
		layoutOptions.setPanesOptions(panes);
		// north pane
		LayoutOptions north = new LayoutOptions();
		north.addOption("resizable", northLayout.isResizable());
		north.addOption("closable", northLayout.isClosable());
		north.addOption("size", northLayout.getSize());
		north.addOption("spacing_open", northLayout.getSpacingOpen());
		layoutOptions.setNorthOptions(north);
		// north_options
		LayoutOptions optionsNorth = new LayoutOptions();
		north.setChildOptions(optionsNorth);
		// north_north pane
		LayoutOptions northNorth = new LayoutOptions();
		northNorth.addOption("initHidden", north_northLayout.isInitHidden());
		northNorth.addOption("initClosed", north_northLayout.isInitClosed());
		northNorth.addOption("closable", north_northLayout.isClosable());
		northNorth.addOption("resizable", north_northLayout.isResizable());
		northNorth
				.addOption("spacing_open", north_northLayout.getSpacingOpen());
		optionsNorth.setNorthOptions(northNorth);
		// north_south pane
		LayoutOptions northSouth = new LayoutOptions();
		northSouth.addOption("initHidden", north_southLayout.isInitHidden());
		northSouth.addOption("initClosed", north_southLayout.isInitClosed());
		northSouth.addOption("closable", north_southLayout.isClosable());
		northSouth.addOption("resizable", north_southLayout.isResizable());
		northSouth
				.addOption("spacing_open", north_southLayout.getSpacingOpen());
		optionsNorth.setSouthOptions(northSouth);
		// north_west pane
		LayoutOptions northWest = new LayoutOptions();
		northWest.addOption("initHidden", north_westLayout.isInitHidden());
		northWest.addOption("initClosed", north_westLayout.isInitClosed());
		northWest.addOption("closable", north_westLayout.isClosable());
		northWest.addOption("resizable", north_westLayout.isResizable());
		northWest.addOption("spacing_open", north_westLayout.getSpacingOpen());
		optionsNorth.setWestOptions(northWest);
		// north_east pane
		LayoutOptions northEast = new LayoutOptions();
		northEast.addOption("initHidden", north_eastLayout.isInitHidden());
		northEast.addOption("initClosed", north_eastLayout.isInitClosed());
		northEast.addOption("closable", north_eastLayout.isClosable());
		northEast.addOption("resizable", north_eastLayout.isResizable());
		northEast.addOption("spacing_open", north_eastLayout.getSpacingOpen());
		optionsNorth.setEastOptions(northEast);
		// north_center pane
		LayoutOptions northCenter = new LayoutOptions();
		northCenter.addOption("closable", north_centerLayout.isClosable());
		northCenter.addOption("resizable", north_centerLayout.isResizable());
		northCenter.addOption("spacing_open",
				north_centerLayout.getSpacingOpen());
		optionsNorth.setCenterOptions(northCenter);

		// south pane
		LayoutOptions south = new LayoutOptions();
		south.addOption("resizable", southLayout.isResizable());
		south.addOption("closable", southLayout.isClosable());
		south.addOption("size", southLayout.getSize());
		south.addOption("spacing_open", southLayout.getSpacingOpen());
		layoutOptions.setSouthOptions(south);

		// south_options
		LayoutOptions optionsSouth = new LayoutOptions();
		south.setChildOptions(optionsSouth);
		// south_north pane
		LayoutOptions southNorth = new LayoutOptions();
		southNorth.addOption("initHidden", south_northLayout.isInitHidden());
		southNorth.addOption("initClosed", south_northLayout.isInitClosed());
		southNorth.addOption("closable", south_northLayout.isClosable());
		southNorth.addOption("resizable", south_northLayout.isResizable());
		southNorth
				.addOption("spacing_open", south_northLayout.getSpacingOpen());
		optionsSouth.setNorthOptions(southNorth);
		// south_south pane
		LayoutOptions southSouth = new LayoutOptions();
		southSouth.addOption("initHidden", south_southLayout.isInitHidden());
		southSouth.addOption("initClosed", south_southLayout.isInitClosed());
		southSouth.addOption("closable", south_southLayout.isClosable());
		southSouth.addOption("resizable", south_southLayout.isResizable());
		southSouth
				.addOption("spacing_open", south_southLayout.getSpacingOpen());
		optionsSouth.setSouthOptions(southSouth);
		// south_west pane
		LayoutOptions southWest = new LayoutOptions();
		southWest.addOption("initHidden", south_westLayout.isInitHidden());
		southWest.addOption("initClosed", south_westLayout.isInitClosed());
		southWest.addOption("closable", south_westLayout.isClosable());
		southWest.addOption("resizable", south_westLayout.isResizable());
		southWest.addOption("spacing_open", south_westLayout.getSpacingOpen());
		optionsSouth.setWestOptions(southWest);
		// south_east pane
		LayoutOptions southEast = new LayoutOptions();
		southEast.addOption("initHidden", south_eastLayout.isInitHidden());
		southEast.addOption("initClosed", south_eastLayout.isInitClosed());
		southEast.addOption("closable", south_eastLayout.isClosable());
		southEast.addOption("resizable", south_eastLayout.isResizable());
		southEast.addOption("spacing_open", south_eastLayout.getSpacingOpen());
		optionsSouth.setEastOptions(southEast);
		// south_center pane
		LayoutOptions southCenter = new LayoutOptions();
		southCenter.addOption("closable", south_centerLayout.isClosable());
		southCenter.addOption("resizable", south_centerLayout.isResizable());
		southCenter.addOption("spacing_open",
				south_centerLayout.getSpacingOpen());
		optionsSouth.setCenterOptions(southCenter);

		// west pane
		LayoutOptions west = new LayoutOptions();
		west.addOption("resizable", westLayout.isResizable());
		west.addOption("closable", westLayout.isClosable());
		west.addOption("size", westLayout.getSize());
		west.addOption("minSize", westLayout.getMinSize());
		west.addOption("maxSize", westLayout.getMaxSize());
		west.addOption("spacing_open", westLayout.getSpacingOpen());
		west.addOption("spacing_closed", westLayout.getSpacingClosed());
		layoutOptions.setWestOptions(west);
		// west-options
		LayoutOptions optionsWest = new LayoutOptions();
		west.setChildOptions(optionsWest);
		// west_north pane
		LayoutOptions westNorth = new LayoutOptions();
		westNorth.addOption("size", west_northLayout.getSize());
		westNorth.addOption("closable", west_northLayout.isClosable());
		westNorth.addOption("resizable", west_northLayout.isResizable());
		westNorth.addOption("spacing_open", west_northLayout.getSpacingOpen());
		optionsWest.setNorthOptions(westNorth);
		// west_south pane
		LayoutOptions westSouth = new LayoutOptions();
		westSouth.addOption("size", west_southLayout.getSize());
		westSouth.addOption("closable", west_southLayout.isClosable());
		westSouth.addOption("resizable", west_southLayout.isResizable());
		westSouth.addOption("spacing_open", west_southLayout.getSpacingOpen());
		optionsWest.setSouthOptions(westSouth);
		// west_west pane
		LayoutOptions westWest = new LayoutOptions();
		westWest.addOption("initHidden", west_westLayout.isInitHidden());
		westWest.addOption("initClosed", west_westLayout.isInitClosed());
		westWest.addOption("closable", west_westLayout.isClosable());
		westWest.addOption("resizable", west_westLayout.isResizable());
		westWest.addOption("spacing_open", west_westLayout.getSpacingOpen());
		optionsWest.setWestOptions(westWest);
		// west_east pane
		LayoutOptions westEast = new LayoutOptions();
		westEast.addOption("initHidden", west_eastLayout.isInitHidden());
		westEast.addOption("initClosed", west_eastLayout.isInitClosed());
		westEast.addOption("closable", west_eastLayout.isClosable());
		westEast.addOption("resizable", west_eastLayout.isResizable());
		westEast.addOption("spacing_open", west_eastLayout.getSpacingOpen());
		optionsWest.setEastOptions(westEast);
		// west_center pane
		LayoutOptions westCenter = new LayoutOptions();
		westCenter.addOption("minHeight", west_centerLayout.getMinHeight());
		westCenter.addOption("closable", west_centerLayout.isClosable());
		westCenter.addOption("resizable", west_centerLayout.isResizable());
		westCenter
				.addOption("spacing_open", west_centerLayout.getSpacingOpen());
		optionsWest.setCenterOptions(westCenter);

		// east pane
		LayoutOptions east = new LayoutOptions();
		east.addOption("initHidden", eastLayout.isInitHidden());
		east.addOption("initClosed", eastLayout.isInitClosed());
		east.addOption("size", eastLayout.getSize());
		east.addOption("minSize", eastLayout.getMinSize());
		east.addOption("maxSize", eastLayout.getMaxSize());
		east.addOption("spacing_open", eastLayout.getSpacingOpen());
		layoutOptions.setEastOptions(east);
		// east options
		LayoutOptions optionsEast = new LayoutOptions();
		east.setChildOptions(optionsEast);
		// east_north pane
		LayoutOptions eastNorth = new LayoutOptions();
		eastNorth.addOption("initHidden", east_northLayout.isInitHidden());
		eastNorth.addOption("initClosed", east_northLayout.isInitClosed());
		eastNorth.addOption("closable", east_northLayout.isClosable());
		eastNorth.addOption("resizable", east_northLayout.isResizable());
		eastNorth.addOption("spacing_open", east_northLayout.getSpacingOpen());
		optionsEast.setNorthOptions(eastNorth);
		// east_south pane
		LayoutOptions eastSouth = new LayoutOptions();
		eastSouth.addOption("initHidden", east_southLayout.isInitHidden());
		eastSouth.addOption("initClosed", east_southLayout.isInitClosed());
		eastSouth.addOption("closable", east_southLayout.isClosable());
		eastSouth.addOption("resizable", east_southLayout.isResizable());
		eastSouth.addOption("spacing_open", east_southLayout.getSpacingOpen());
		optionsEast.setSouthOptions(eastSouth);
		// east_west pane
		LayoutOptions eastWest = new LayoutOptions();
		eastWest.addOption("initHidden", east_westLayout.isInitHidden());
		eastWest.addOption("initClosed", east_westLayout.isInitClosed());
		eastWest.addOption("closable", east_westLayout.isClosable());
		eastWest.addOption("resizable", east_westLayout.isResizable());
		eastWest.addOption("spacing_open", east_westLayout.getSpacingOpen());
		optionsEast.setWestOptions(eastWest);
		// east_east pane
		LayoutOptions eastEast = new LayoutOptions();
		eastEast.addOption("initHidden", east_eastLayout.isInitHidden());
		eastEast.addOption("initClosed", east_eastLayout.isInitClosed());
		eastEast.addOption("closable", east_eastLayout.isClosable());
		eastEast.addOption("resizable", east_eastLayout.isResizable());
		eastEast.addOption("spacing_open", east_eastLayout.getSpacingOpen());
		optionsEast.setEastOptions(eastEast);
		// east_center pane
		LayoutOptions eastCenter = new LayoutOptions();
		eastCenter.addOption("minHeight", east_centerLayout.getMinHeight());
		optionsEast.setCenterOptions(eastCenter);

		// center pane
		LayoutOptions center = new LayoutOptions();
		center.addOption("resizable", centerLayout.isResizable());
		center.addOption("closable", centerLayout.isClosable());
		center.addOption("minWidth", centerLayout.getMinWidth());
		center.addOption("minHeight", centerLayout.getMinHeight());
		center.addOption("spacing_open", centerLayout.getSpacingOpen());
		layoutOptions.setCenterOptions(center);
		// center options
		LayoutOptions optionsCenter = new LayoutOptions();
		center.setChildOptions(optionsCenter);
		// center_north pane
		LayoutOptions centerNorth = new LayoutOptions();
		centerNorth.addOption("size", center_northLayout.getSize());
		optionsCenter.setNorthOptions(centerNorth);
		// center_south pane
		LayoutOptions centerSouth = new LayoutOptions();
		centerSouth.addOption("initHidden", center_southLayout.isInitHidden());
		centerSouth.addOption("initClosed", center_southLayout.isInitClosed());
		centerSouth.addOption("closable", center_southLayout.isClosable());
		centerSouth.addOption("resizable", center_southLayout.isResizable());
		centerSouth.addOption("spacing_open",
				center_southLayout.getSpacingOpen());
		optionsCenter.setSouthOptions(centerSouth);
		// center_west pane
		LayoutOptions centerWest = new LayoutOptions();
		centerWest.addOption("initHidden", center_westLayout.isInitHidden());
		centerWest.addOption("initClosed", center_westLayout.isInitClosed());
		centerWest.addOption("closable", center_westLayout.isClosable());
		centerWest.addOption("resizable", center_westLayout.isResizable());
		centerWest
				.addOption("spacing_open", center_westLayout.getSpacingOpen());
		optionsCenter.setWestOptions(centerWest);
		// center_east pane
		LayoutOptions centerEast = new LayoutOptions();
		centerEast.addOption("initHidden", center_eastLayout.isInitHidden());
		centerEast.addOption("initClosed", center_eastLayout.isInitClosed());
		centerEast.addOption("closable", center_eastLayout.isClosable());
		centerEast.addOption("resizable", center_eastLayout.isResizable());
		centerEast
				.addOption("spacing_open", center_eastLayout.getSpacingOpen());
		optionsCenter.setEastOptions(centerEast);
		// center_center pane
		LayoutOptions centerCenter = new LayoutOptions();
		centerCenter.addOption("minHeight", center_centerLayout.getMinHeight());
		optionsCenter.setCenterOptions(centerCenter);
	}

	@Produces
	@Named("layout")
	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	@Produces
	@Named("north")
	public PositionLayout getNorthLayout() {
		return northLayout;
	}

	public void setNorthLayout(PositionLayout northLayout) {
		this.northLayout = northLayout;
	}

	@Produces
	@Named("north_north")
	public PositionLayout getNorth_northLayout() {
		return north_northLayout;
	}

	public void setNorth_northLayout(PositionLayout north_northLayout) {
		this.north_northLayout = north_northLayout;
	}

	@Produces
	@Named("north_south")
	public PositionLayout getNorth_southLayout() {
		return north_southLayout;
	}

	public void setNorth_southLayout(PositionLayout north_southLayout) {
		this.north_southLayout = north_southLayout;
	}

	@Produces
	@Named("north_west")
	public PositionLayout getNorth_westLayout() {
		return north_westLayout;
	}

	public void setNorth_westLayout(PositionLayout north_westLayout) {
		this.north_westLayout = north_westLayout;
	}

	@Produces
	@Named("north_east")
	public PositionLayout getNorth_eastLayout() {
		return north_eastLayout;
	}

	public void setNorth_eastLayout(PositionLayout north_eastLayout) {
		this.north_eastLayout = north_eastLayout;
	}

	@Produces
	@Named("north_center")
	public PositionLayout getNorth_centerLayout() {
		return north_centerLayout;
	}

	public void setNorth_centerLayout(PositionLayout north_centerLayout) {
		this.north_centerLayout = north_centerLayout;
	}

	@Produces
	@Named("south")
	public PositionLayout getSouthLayout() {
		return southLayout;
	}

	public void setSouthLayout(PositionLayout southLayout) {
		this.southLayout = southLayout;
	}

	@Produces
	@Named("south_north")
	public PositionLayout getSouth_northLayout() {
		return south_northLayout;
	}

	public void setSouth_northLayout(PositionLayout south_northLayout) {
		this.south_northLayout = south_northLayout;
	}

	@Produces
	@Named("south_south")
	public PositionLayout getSouth_southLayout() {
		return south_southLayout;
	}

	public void setSouth_southLayout(PositionLayout south_southLayout) {
		this.south_southLayout = south_southLayout;
	}

	@Produces
	@Named("south_west")
	public PositionLayout getSouth_westLayout() {
		return south_westLayout;
	}

	public void setSouth_westLayout(PositionLayout south_westLayout) {
		this.south_westLayout = south_westLayout;
	}

	@Produces
	@Named("south_east")
	public PositionLayout getSouth_eastLayout() {
		return south_eastLayout;
	}

	public void setSouth_eastLayout(PositionLayout south_eastLayout) {
		this.south_eastLayout = south_eastLayout;
	}

	@Produces
	@Named("south_center")
	public PositionLayout getSouth_centerLayout() {
		return south_centerLayout;
	}

	public void setSouth_centerLayout(PositionLayout south_centerLayout) {
		this.south_centerLayout = south_centerLayout;
	}

	@Produces
	@Named("west")
	public PositionLayout getWestLayout() {
		return westLayout;
	}

	public void setWestLayout(PositionLayout westLayout) {
		this.westLayout = westLayout;
	}

	@Produces
	@Named("west_north")
	public PositionLayout getWest_northLayout() {
		return west_northLayout;
	}

	public void setWest_northLayout(PositionLayout west_northLayout) {
		this.west_northLayout = west_northLayout;
	}

	@Produces
	@Named("west_south")
	public PositionLayout getWest_southLayout() {
		return west_southLayout;
	}

	public void setWest_southLayout(PositionLayout west_southLayout) {
		this.west_southLayout = west_southLayout;
	}

	@Produces
	@Named("west_west")
	public PositionLayout getWest_westLayout() {
		return west_westLayout;
	}

	public void setWest_westLayout(PositionLayout west_westLayout) {
		this.west_westLayout = west_westLayout;
	}

	@Produces
	@Named("west_east")
	public PositionLayout getWest_eastLayout() {
		return west_eastLayout;
	}

	public void setWest_eastLayout(PositionLayout west_eastLayout) {
		this.west_eastLayout = west_eastLayout;
	}

	@Produces
	@Named("west_center")
	public PositionLayout getWest_centerLayout() {
		return west_centerLayout;
	}

	public void setWest_centerLayout(PositionLayout west_centerLayout) {
		this.west_centerLayout = west_centerLayout;
	}

	@Produces
	@Named("east")
	public PositionLayout getEastLayout() {
		return eastLayout;
	}

	public void setEastLayout(PositionLayout eastLayout) {
		this.eastLayout = eastLayout;
	}

	@Produces
	@Named("east_north")
	public PositionLayout getEast_northLayout() {
		return east_northLayout;
	}

	public void setEast_northLayout(PositionLayout east_northLayout) {
		this.east_northLayout = east_northLayout;
	}

	@Produces
	@Named("east_south")
	public PositionLayout getEast_southLayout() {
		return east_southLayout;
	}

	public void setEast_southLayout(PositionLayout east_southLayout) {
		this.east_southLayout = east_southLayout;
	}

	@Produces
	@Named("east_west")
	public PositionLayout getEast_westLayout() {
		return east_westLayout;
	}

	public void setEast_westLayout(PositionLayout east_westLayout) {
		this.east_westLayout = east_westLayout;
	}

	@Produces
	@Named("east_east")
	public PositionLayout getEast_eastLayout() {
		return east_eastLayout;
	}

	public void setEast_eastLayout(PositionLayout east_eastLayout) {
		this.east_eastLayout = east_eastLayout;
	}

	@Produces
	@Named("east_center")
	public PositionLayout getEast_centerLayout() {
		return east_centerLayout;
	}

	public void setEast_centerLayout(PositionLayout east_centerLayout) {
		this.east_centerLayout = east_centerLayout;
	}

	@Produces
	@Named("center")
	public PositionLayout getCenterLayout() {
		return centerLayout;
	}

	public void setCenterLayout(PositionLayout centerLayout) {
		this.centerLayout = centerLayout;
	}

	@Produces
	@Named("center_north")
	public PositionLayout getCenter_northLayout() {
		return center_northLayout;
	}

	public void setCenter_northLayout(PositionLayout center_northLayout) {
		this.center_northLayout = center_northLayout;
	}

	@Produces
	@Named("center_south")
	public PositionLayout getCenter_southLayout() {
		return center_southLayout;
	}

	public void setCenter_southLayout(PositionLayout center_southLayout) {
		this.center_southLayout = center_southLayout;
	}

	@Produces
	@Named("center_west")
	public PositionLayout getCenter_westLayout() {
		return center_westLayout;
	}

	public void setCenter_westLayout(PositionLayout center_westLayout) {
		this.center_westLayout = center_westLayout;
	}

	@Produces
	@Named("center_east")
	public PositionLayout getCenter_eastLayout() {
		return center_eastLayout;
	}

	public void setCenter_eastLayout(PositionLayout center_eastLayout) {
		this.center_eastLayout = center_eastLayout;
	}

	@Produces
	@Named("center_center")
	public PositionLayout getCenter_centerLayout() {
		return center_centerLayout;
	}

	public void setCenter_centerLayout(PositionLayout center_centerLayout) {
		this.center_centerLayout = center_centerLayout;
	}

	private Map<String, PositionLayout> createPositionLayout(Layout layout) {
		for (PositionLayout positionLayout : layout.getPositionLayouts()) {
			pLayout.put(positionLayout.getPositionName(), positionLayout);
		}
		return pLayout;
	}

	public LayoutOptions getLayoutOptions() {
		return layoutOptions;
	}

	public void setLayoutOptions(LayoutOptions layoutOptions) {
		this.layoutOptions = layoutOptions;
	}

	//
	// public void showMessages() {
	// FacesContext fc = FacesContext.getCurrentInstance();
	// FacesMessage msg1 = new FacesMessage(FacesMessage.SEVERITY_ERROR,
	// "This is the first error message", null);
	// FacesMessage msg2 = new FacesMessage(FacesMessage.SEVERITY_ERROR,
	// "This is the second error message", null);
	//
	// fc.addMessage(null, msg1);
	// fc.addMessage(null, msg2);
	// }
	//
	// public void hideMessages() {
	// }
}
