package com.github.handwriteppt;

import java.util.Properties;

import javax.swing.Icon;

import com.github.handwriteppt.projector.Projector;

public class ShowLabel extends ClickableLabel
{

  public ShowLabel(Icon image)
  {
    super(image);
    // TODO Auto-generated constructor stub
  }
//
//  public ShowLabel(String text, JFrame controller)
//  {
//    super(text, controller);
//    // TODO Auto-generated constructor stub
//  }

  @Override
  public void mouseClicked()
  {
    DrawPad.getInstance().saveMaterial();
    Properties properties = System.getProperties();
    Projector.show(properties.getProperty("user.dir") + "\\test.zip");
  }

}
