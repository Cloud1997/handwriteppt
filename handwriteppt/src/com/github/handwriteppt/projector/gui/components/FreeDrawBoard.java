package com.github.handwriteppt.projector.gui.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JLabel;

public class FreeDrawBoard extends JLabel {

	private int x = -1, y = -1;
	private Graphics2D drawer;
	public static final int NOT_READY=-1;
	public static final int READY=0;
	public static final int DRAWED=1;
	private int status=NOT_READY;
	
	private Color c=Color.BLUE;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void initDrawer() {
		if (drawer == null) {
			drawer = (Graphics2D) getGraphics();
			Stroke stroke = new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
			drawer.setStroke(stroke);
			drawer.setColor(c);
			status=READY;
			
		}
	}
	
	public int getStatus(){
		return status;
	}
	

	public void drawToPoint(int x, int y) {
		if (this.x < 0 || this.y < 0) {
//			drawer.drawLine(x, y, x, y);
		} else {
			drawer.drawLine(this.x, this.y, x, y);
		}
		this.x = x;
		this.y = y;
		status=DRAWED;
	}

	public void resetXY() {
		this.x = -1;
		this.y = -1;
	}
	
	public void clearBoard(){
		
		status=READY;
	}
	
	

	public void changeColor(Color c) {
		this.c=c;
		if(drawer!=null){
		drawer.setColor(c);
		}
	}


}
