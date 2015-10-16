package com.github.handwriteppt;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class ScreenUtil {

	private static Dimension screenSize;//Donot Set!!!
	private static Cursor pen,eraser;
	public static final int PEN=0;
	public static final int ERASER=1;
	
	public static Dimension getScreenSize(){
		if(screenSize==null){
			screenSize=Toolkit.getDefaultToolkit().getScreenSize();
	    }
		return screenSize;
	}
	
	public static Dimension getWindowSize(){
		Rectangle rec=	DrawPad.getInstance().getBounds();
		return new Dimension(rec.width, rec.height);
	}
	
	public static void setCursor(int type){
		if(pen==null){
			pen=Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("res/PenCursor.png").getImage(), new Point(0,30), "Pen");
		}
		if(eraser==null){
			eraser=Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("res/EraserCursor.png").getImage(), new Point(0,15), "Eraser");
		}
		
		if(type==PEN){
			DrawPad.getInstance().setCursor(pen);
		}
		if(type==ERASER){
			DrawPad.getInstance().setCursor(eraser);
		}
	}
}
