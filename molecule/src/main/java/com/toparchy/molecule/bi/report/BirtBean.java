//package com.toparchy.molecule.bi.report;
//
//import javax.enterprise.context.SessionScoped;
//import javax.inject.Named;
//
//import java.io.Serializable;
//
//import org.eclipse.birt.report.engine.api.EngineException;
//
//@Named
//@SessionScoped
//public class BirtBean implements Serializable {
//
//	private static final long serialVersionUID = 7859020695443611979L;
//	private String name;
//
//	public String getReport() {
//		birt2jsf report = new birt2jsf();
//		String reportHTML = null;
//		try {
//			reportHTML = report.executeReport("76000DWT-1");
//
//		} catch (EngineException e) {
//			e.printStackTrace();
//		}
//
//		return reportHTML;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//}