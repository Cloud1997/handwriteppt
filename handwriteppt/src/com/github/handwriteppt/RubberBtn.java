package com.github.handwriteppt;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JFrame;

public class RubberBtn extends ActionButton
{
  private boolean isPressed = false;

  public boolean isRubberMode()
  {
    return isPressed;
  }

  public void setRubberMode(boolean isPressed)
  {
    this.isPressed = isPressed;
    setBackground(isPressed ? Color.YELLOW : new Color(238, 238, 238));
  }

  public RubberBtn(Action a, JFrame parent)
  {
    super(a, parent);
  }

  public RubberBtn(Icon icon, JFrame parent)
  {
    super(icon, parent);
  }

  public RubberBtn(JFrame parent)
  {
    super(parent);
  }

  public RubberBtn(String text, Icon icon, JFrame parent)
  {
    super(text, icon, parent);
  }

  public RubberBtn(String text, JFrame parent)
  {
    super(text, parent);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    setRubberMode(!isPressed);
  }

}
