package com.github.handwriteppt;

import javax.swing.Icon;

public class NewLayerLabel extends ClickableLabel
{

//  public NewLayerLabel(String text, JFrame controller)
//  {
//    super(text, controller);
//    // TODO Auto-generated constructor stub
//  }

  public NewLayerLabel(Icon image)
  {
    super(image);
  }

  @Override
  public void mouseClicked()
  {
	  DrawPad.getInstance().addNewLayerToCurrentPage();
  }


}
