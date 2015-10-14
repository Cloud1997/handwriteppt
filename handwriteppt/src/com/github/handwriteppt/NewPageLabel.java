package com.github.handwriteppt;

import javax.swing.Icon;

public class NewPageLabel extends ClickableLabel
{

  public NewPageLabel(Icon image)
  {
    super(image);
  }

//  public NewPageLabel(String text, JFrame controller)
//  {
//    super(text, controller);
//  }

  @Override
  public void mouseClicked()
  {
    DrawPad.getInstance().addNewPage();
  }

}
