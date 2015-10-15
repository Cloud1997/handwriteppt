package com.github.handwriteppt;

import java.awt.event.ActionEvent;
import java.util.Properties;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JFrame;

import com.github.handwriteppt.projector.Projector;

public class ShowBtn extends ActionButton
{

  public ShowBtn(Action a, JFrame parent)
  {
    super(a, parent);
    // TODO Auto-generated constructor stub
  }

  public ShowBtn(Icon icon, JFrame parent)
  {
    super(icon, parent);
    // TODO Auto-generated constructor stub
  }

  public ShowBtn(JFrame parent)
  {
    super(parent);
    // TODO Auto-generated constructor stub
  }

  public ShowBtn(String text, Icon icon, JFrame parent)
  {
    super(text, icon, parent);
    // TODO Auto-generated constructor stub
  }

  public ShowBtn(String text, JFrame parent)
  {
    super(text, parent);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    Properties properties = System.getProperties();
//    Projector.show(properties.getProperty("user.dir") + "\\test.zip");
    Projector.show("res/show.zip");
  }

}
