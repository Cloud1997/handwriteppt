package com.github.handwriteppt;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PresentationLayer extends JPanel
{
  private String  name;
  private boolean isHidden;

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public boolean isHidden()
  {
    return isHidden;
  }

  public void setHidden(boolean isHidden)
  {
    this.isHidden = isHidden;
  }

  public PresentationLayer(String name)
  {
    super();
    this.name = name;
    setLayout(new BorderLayout());
    isHidden = false;
  }

  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    g.drawString(name, 500, 500);
  }

}
