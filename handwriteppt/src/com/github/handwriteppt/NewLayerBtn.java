package com.github.handwriteppt;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JFrame;

public class NewLayerBtn extends ActionButton
{

  public NewLayerBtn(Action a, JFrame parent)
  {
    super(a, parent);
    // TODO Auto-generated constructor stub
  }

  public NewLayerBtn(Icon icon, JFrame parent)
  {
    super(icon, parent);
    // TODO Auto-generated constructor stub
  }

  public NewLayerBtn(JFrame parent)
  {
    super(parent);
    // TODO Auto-generated constructor stub
  }

  public NewLayerBtn(String text, Icon icon, JFrame parent)
  {
    super(text, icon, parent);
    // TODO Auto-generated constructor stub
  }

  public NewLayerBtn(String text, JFrame parent)
  {
    super(text, parent);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    ((DrawPad)parent).addNewLayerToCurrentPage();
  }

}
