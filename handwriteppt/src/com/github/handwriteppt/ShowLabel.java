package com.github.handwriteppt;

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
    String tmpFilePath = System.getProperty("user.home") + "\\handwritetmp.zip";
    DrawPad.getInstance().saveMaterial(tmpFilePath);
    Projector.show(tmpFilePath);
  }

}
