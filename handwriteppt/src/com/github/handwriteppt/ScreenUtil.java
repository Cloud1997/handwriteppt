package com.github.handwriteppt;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ScreenUtil {

	private static Dimension screenSize;//Donot Set!!!
	
	public static Dimension getScreenSize(){
		if(screenSize==null){
			screenSize=Toolkit.getDefaultToolkit().getScreenSize();
	    }
		return screenSize;
	}
}
