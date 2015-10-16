package com.github.handwriteppt;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class PresentationPage extends JLayeredPane
    implements MouseListener, MouseMotionListener
{
  private DefaultListModel<PresentationLayer> layers              = new DefaultListModel<PresentationLayer>();
  private JFrame                              parent;
  private boolean                             isLeftButtonPressed = false;

  public DefaultListModel<PresentationLayer> getLayers()
  {
    return layers;
  }

  private String name;

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public PresentationPage(String name, JFrame parent)
  {
    super();
    this.name = name;
    this.parent = parent;
    PresentationLayer newLayer = new PresentationLayer(String.format(ConsantList.LAYER_NAME_FORMAT, 1), true,
        parent);
    layers.add(0, newLayer);
    add(newLayer, new Integer(0));
    addMouseListener(this);
    addMouseMotionListener(this);
  }

  public PresentationLayer getLayerByIndex(int index)
  {
    return layers.get(index);
  }

  public void addNewLayer()
  {
    PresentationLayer newLayer = new PresentationLayer(
        String.format(ConsantList.LAYER_NAME_FORMAT, layers.size() + 1), false, parent);
    layers.add(0, newLayer);
    add(newLayer,  Integer.valueOf(layers.size()-1));
  }

  @Override
  public String toString()
  {
    return name;
  }

  @Override
  public void mouseDragged(MouseEvent e)
  {
    DrawPad drawPad = (DrawPad)parent;
    if (drawPad.getSelectedLayer().isHidden())
    {
      return;
    }
    if (isLeftButtonPressed)
    {
      if (drawPad.isRubberMode())
      {
        drawPad.getSelectedLayer().erase(e.getPoint());
      }
      else
      {
        drawPad.getSelectedLayer().drawLine(e.getPoint());
      }
      parent.repaint();
    }

  }

  @Override
  public void mouseMoved(MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseClicked(MouseEvent e)
  {

  }

  @Override
  public void mousePressed(MouseEvent e)
  {
    DrawPad drawPad = (DrawPad)parent;
    if (drawPad.getSelectedLayer().isHidden())
    {
      return;
    }
    if (e.getButton() == MouseEvent.BUTTON1)
    {
      isLeftButtonPressed = true;
      drawPad.getSelectedLayer().startToDraw(e.getPoint());
      parent.repaint();
    }
    else
    {
      isLeftButtonPressed = false;
    }
  }

  @Override
  public void mouseReleased(MouseEvent e)
  {
    isLeftButtonPressed = false;
    if (e.getButton() == MouseEvent.BUTTON3)
    {
      ((DrawPad)parent).getSelectedLayer().undoLastDraw();
      parent.repaint();
    }

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
