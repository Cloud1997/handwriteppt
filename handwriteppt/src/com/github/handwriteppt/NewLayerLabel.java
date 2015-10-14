package com.github.handwriteppt;

import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JFrame;

public class NewLayerLabel extends ClickableLabel
{

  public NewLayerLabel(String text, JFrame controller)
  {
    super(text, controller);
    // TODO Auto-generated constructor stub
  }

  public NewLayerLabel(Icon image, JFrame controller)
  {
    super(image, controller);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    ((DrawPad)controller).addNewLayerToCurrentPage();
  }

}
