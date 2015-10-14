package com.github.handwriteppt;

import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JFrame;

public class SaveLabel extends ClickableLabel
{

  public SaveLabel(Icon image, JFrame controller)
  {
    super(image, controller);
    // TODO Auto-generated constructor stub
  }

  public SaveLabel(String text, JFrame controller)
  {
    super(text, controller);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    ((DrawPad)controller).saveMaterial();
  }

}
