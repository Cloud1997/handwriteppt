package com.github.handwriteppt;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class RubberLabel extends ClickableLabel
{
  private boolean working = false;
  private Icon rest,work;

  public RubberLabel(Icon rest,Icon work)
  {
    super(rest);
    this.rest=rest;
    this.work=work;
 
  }

  public boolean isRubberMode()
  {
    return working;
  }

  private void setRubberMode(boolean isPressed)
  {
    this.working = isPressed;
    setIcon(working?work:rest);
    ScreenUtil.setCursor(working?ScreenUtil.ERASER:ScreenUtil.PEN);
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
