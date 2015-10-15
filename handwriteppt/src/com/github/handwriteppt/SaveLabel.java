package com.github.handwriteppt;

import javax.swing.Icon;

public class SaveLabel extends ClickableLabel
{

  public SaveLabel(Icon image)
  {
    super(image);
    // TODO Auto-generated constructor stub
  }

  //  public SaveLabel(String text, JFrame controller)
  //  {
  //    super(text, controller);
  //    // TODO Auto-generated constructor stub
  //  }

  @Override
  public void mouseClicked()
  {
    DrawPad.getInstance().saveMaterial(DrawPad.getInstance().getCurrentFileName());
  }

}
