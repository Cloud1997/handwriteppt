package com.github.handwriteppt;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class RubberLabel extends ClickableLabel
{
  private boolean working = false;
  private Icon rest,work;
  Cursor c;

  public RubberLabel(Icon rest,Icon work)
  {
    super(rest);
    this.rest=rest;
    this.work=work;
    ImageIcon icon=new ImageIcon("res/PenCursor.png");
    System.out.println(icon.getIconWidth()+"   "+icon.getIconHeight());
    Toolkit.getDefaultToolkit().getBestCursorSize(20, 20);
    c= Toolkit.getDefaultToolkit().createCustomCursor(icon.getImage(), new Point(0,0), "Pen");
 
  }

//  public RubberLabel(String text, JFrame controller)
//  {
//    super(text, controller);
//  }

  public boolean isRubberMode()
  {
    return working;
  }

  private void setRubberMode(boolean isPressed)
  {
    this.working = isPressed;
    setIcon(working?work:rest);
    DrawPad.getInstance().setCursor(c);
  }

  @Override
  public void mouseClicked()
  {
    setRubberMode(!working);
  }

@Override
protected void mousePressed() {
if(working){
	setBorder(upBoader);
}else{
	setBorder(downBoader);
}
}

@Override
protected void mouseExited() {
	if(working){	
	}else{
		setBorder(null);
	}
}

@Override
protected void mouseEntered() {
	if(working){
	}else{
	setBorder(upBoader);
	}
}

@Override
protected void mouseReleased() {
	// TODO Auto-generated method stub
//	if(working){
//		setBorder(upBoader);
//	}else{
//		
//	}
//	super.mouseReleased();
}
  
  
}
