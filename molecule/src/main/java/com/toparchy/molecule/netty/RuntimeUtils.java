package com.toparchy.molecule.netty;

public class RuntimeUtils {

	private static Runtime runtime = Runtime.getRuntime();
	
	public static int getProcessors(){
		return runtime.availableProcessors() ;
	}
	
}
