package com.github.handwriteppt;

import java.awt.Color;

import javax.swing.Icon;

public class RubberLabel extends ClickableLabel
{
  private boolean isPressed = false;

  public RubberLabel(Icon image)
  {
    super(image);
  }

//  public RubberLabel(String text, JFrame controller)
//  {
//    super(text, controller);
//  }

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
  public void mouseClicked()
  {
    setRubberMode(!isPressed);
  }
}
