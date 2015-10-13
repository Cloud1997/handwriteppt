package com.github.handwriteppt;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JFrame;

public class SaveBtn extends ActionButton
{

  public SaveBtn(Action a, JFrame parent)
  {
    super(a, parent);
    // TODO Auto-generated constructor stub
  }

  public SaveBtn(Icon icon, JFrame parent)
  {
    super(icon, parent);
    // TODO Auto-generated constructor stub
  }

  public SaveBtn(JFrame parent)
  {
    super(parent);
    // TODO Auto-generated constructor stub
  }

  public SaveBtn(String text, Icon icon, JFrame parent)
  {
    super(text, icon, parent);
    // TODO Auto-generated constructor stub
  }

  public SaveBtn(String text, JFrame parent)
  {
    super(text, parent);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    ((DrawPad)parent).saveMaterial();
  }

}
