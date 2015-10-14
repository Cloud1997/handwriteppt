package com.github.handwriteppt;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ClickableLabel extends JLabel
    implements MouseListener
{
  protected JFrame controller;

  public ClickableLabel(JFrame controller)
  {
    super();
    addMouseListener(this);
  }

  public ClickableLabel(Icon image, JFrame controller)
  {
    super(image);
    this.controller = controller;
    addMouseListener(this);
  }

  public ClickableLabel(String text, JFrame controller)
  {
    super(text);
    this.controller = controller;
    addMouseListener(this);
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {

  }

  @Override
  public void mousePressed(MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseReleased(MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseEntered(MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

}
