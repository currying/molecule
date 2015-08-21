//package com.toparchy.molecule.bi.report;
//
//import java.io.ByteArrayOutputStream;
//
//import javax.faces.context.FacesContext;
//import javax.servlet.ServletContext;
//
//import org.eclipse.birt.core.framework.Platform;
//import org.eclipse.birt.report.engine.api.EngineConfig;
//import org.eclipse.birt.report.engine.api.EngineException;
//import org.eclipse.birt.report.engine.api.PDFRenderOption;
//import org.eclipse.birt.report.engine.api.IReportEngine;
//import org.eclipse.birt.report.engine.api.IReportEngineFactory;
//import org.eclipse.birt.report.engine.api.IReportRunnable;
//import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
//import org.eclipse.core.internal.registry.RegistryProviderFactory;
//
//public class birt2jsf {
//
//	String executeReport(String param) throws EngineException {
//		IReportEngine engine = null;
//		EngineConfig config = null;
//		ServletContext sc = (ServletContext) FacesContext.getCurrentInstance()
//				.getExternalContext().getContext();
//		try {
//			config = new EngineConfig();
//			config.setLogConfig(sc.getRealPath("/report/logs"),
//					java.util.logging.Level.FINEST);
//			config.setResourcePath(sc.getRealPath("/report"));
//			config.setTempDir(sc.getRealPath("/report/report/images"));
//			Platform.startup(config);
//
//			// create new Report Engine
//			IReportEngineFactory factory = (IReportEngineFactory) Platform
//					.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
//			engine = factory.createReportEngine(config);
//
//			// open the report design
//			IReportRunnable design = null;
//			design = engine
//					.openReportDesign("E:\\work\\java_workspace\\wildfly-8.2.0.Final\\standalone\\deployments\\molecule.war\\report\\rptdesigns\\scjszb.rptdesign");
//
//			// create RunandRender Task
//			IRunAndRenderTask task = engine.createRunAndRenderTask(design);
//
//			// pass necessary parameters
//			task.setParameterValue("gcbh", param);
//			task.validateParameters();
//
//			// set render options including output type
//			PDFRenderOption options = new PDFRenderOption();
//			ByteArrayOutputStream outs = new ByteArrayOutputStream();
//			options.setOutputStream(outs);
//			options.setOutputFormat("html");
//			task.setRenderOption(options);
//
//			// run task
//			String output;
//			task.run();
//			output = outs.toString();
//			task.close();
//			engine.destroy();
//			return output;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return "Error";
//		} finally {
//			Platform.shutdown();
//			RegistryProviderFactory.releaseDefault();
//		}
//	}
//}
