package com.toparchy.molecule.push.netty;

public class RuntimeUtils {

	private static Runtime runtime = Runtime.getRuntime();
	
	public static int getProcessors(){
		return runtime.availableProcessors() ;
	}
	
}
