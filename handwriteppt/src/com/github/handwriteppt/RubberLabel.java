package com.github.handwriteppt;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JFrame;

public class RubberLabel extends ClickableLabel
{
  private boolean isPressed = false;

  public RubberLabel(Icon image, JFrame controller)
  {
    super(image, controller);
  }

  public RubberLabel(String text, JFrame controller)
  {
    super(text, controller);
  }

  public boolean isRubberMode()
  {
    return isPressed;
  }

  public void setRubberMode(boolean isPressed)
  {
    this.isPressed = isPressed;
    setBackground(isPressed ? Color.YELLOW : new Color(238, 238, 238));
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    setRubberMode(!isPressed);
  }
}
