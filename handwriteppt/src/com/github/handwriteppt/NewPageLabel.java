package com.github.handwriteppt;

import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JFrame;

public class NewPageLabel extends ClickableLabel
{

  public NewPageLabel(Icon image, JFrame controller)
  {
    super(image, controller);
  }

  public NewPageLabel(String text, JFrame controller)
  {
    super(text, controller);
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    ((DrawPad)controller).addNewPage();
  }

}
