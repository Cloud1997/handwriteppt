package com.github.handwriteppt;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GridBagLayoutUtil {
	
	private static final GridBagConstraints gc=new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0),0 ,0 );
	
	public static GridBagConstraints getDefaultConstraints(){
		return (GridBagConstraints) gc.clone();
	}

}
