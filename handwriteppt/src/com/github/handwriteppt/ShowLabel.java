package com.github.handwriteppt;

import java.awt.event.MouseEvent;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.JFrame;

import com.github.handwriteppt.projector.Projector;

public class ShowLabel extends ClickableLabel
{

  public ShowLabel(Icon image, JFrame controller)
  {
    super(image, controller);
    // TODO Auto-generated constructor stub
  }

  public ShowLabel(String text, JFrame controller)
  {
    super(text, controller);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    ((DrawPad)controller).saveMaterial();
    Properties properties = System.getProperties();
    Projector.show(properties.getProperty("user.dir") + "\\test.zip");
  }

}
