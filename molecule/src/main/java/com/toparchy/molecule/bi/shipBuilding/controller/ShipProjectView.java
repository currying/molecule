package com.toparchy.molecule.bi.shipBuilding.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Model;
import javax.faces.view.ViewScoped;

import org.primefaces.context.RequestContext;

import com.toparchy.molecule.bi.shipBuilding.model.KanBan;

@Model
@ViewScoped
public class ShipProjectView implements Serializable {

	private static final long serialVersionUID = -1233802418507195043L;

	public void viewScheduler(String shipTypeName, String scheduler, String gcbh) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", true);
		options.put("resizable", false);
		options.put("includeViewParams", true);
		options.put("contentHeight", 500);
		options.put("contentWidth", 1280);
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		List<String> param1 = new ArrayList<String>();
		List<String> param2 = new ArrayList<String>();
		param1.add(gcbh);
		param2.add(shipTypeName);
		params.put("gcbh", param1);
		params.put("shipTypeName", param2);
		RequestContext.getCurrentInstance().openDialog(
				"scheduler/" + scheduler, options, params);
	}

	public void viewKanban(String shipTypeName, KanBan kanban, String gcbh) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", true);
		options.put("resizable", false);
		options.put("includeViewParams", true);
		options.put("contentHeight", 500);
		options.put("contentWidth", 1280);
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		List<String> param1 = new ArrayList<String>();
		List<String> param2 = new ArrayList<String>();
		param1.add(gcbh);
		param2.add(shipTypeName);
		params.put("gcbh", param1);
		params.put("shipTypeName", param2);
		RequestContext.getCurrentInstance().openDialog(
				"kanban/" + kanban.getKanban(), options, params);
	}
}
