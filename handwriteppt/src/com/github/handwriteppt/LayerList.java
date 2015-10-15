package com.github.handwriteppt;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

public class LayerList extends JList<PresentationLayer>
    implements MouseListener
{
  private JFrame          parent;
  private LayerListRender cellRender   = new LayerListRender();
  private int             currentIndex = 0;
  private boolean         isDeleteOp   = false;

  public LayerList(JFrame parent)
  {
    super(new DefaultListModel<PresentationLayer>());
    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.parent = parent;
    setCellRenderer(cellRender);
    addMouseListener(this);
  }

  @Override
  public DefaultListModel<PresentationLayer> getModel()
  {
    return (DefaultListModel)super.getModel();
  }

  public void switchModel(DefaultListModel<PresentationLayer> layers)
  {
    setModel(layers);
  }

  public void deleteLayer(int index)
  {
    DrawPad drawPad = (DrawPad)parent;
    DefaultListModel<PresentationLayer> model = getModel();
    if ((index == 0) && (model.size() > 1))
    {
      model.getElementAt(1).setOpaque(true);
    }
    isDeleteOp = true;
    PresentationLayer deletedLayer = model.get(index);
    drawPad.getSelectedPage().remove(deletedLayer);
    model.removeElementAt(index);
    if (model.size() == 0)
    {
      PresentationLayer newLayer = new PresentationLayer(String.format(ConsantList.LAYER_NAME_FORMAT, 1),
          true, parent);
      model.add(0, newLayer);
      drawPad.getSelectedPage().add(newLayer, new Integer(0));
    }
    currentIndex = 0;
    isDeleteOp = false;
    drawPad.repaint();
  }

  public class LayerListRender
      implements ListCellRenderer<PresentationLayer>
  {
    private JPanel jp = new JPanel(new GridLayout(0, 2));

    @Override
    public Component getListCellRendererComponent(JList< ? extends PresentationLayer> list,
        PresentationLayer value, int index, boolean isSelected, boolean cellHasFocus)
    {
      jp.removeAll();
      JCheckBox cb = new JCheckBox();
      cb.setSelected(!value.isHidden());
      cb.setBackground(list.getBackground());
      jp.add(new JLabel(value.getName()));
      jp.add(cb);
      jp.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
      jp.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
      return jp;
    }

    public boolean isCheckbox(Point p)
    {
      Rectangle rc = jp.getComponent(1).getBounds();
      if ((p.getX() >= rc.getMinX()) && (p.getX() <= rc.getMaxX()))
      {
        return true;
      }
      else
      {
        return false;
      }
    }

  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    int index = locationToIndex(e.getPoint());
    if ((index != -1))
    {
      if ((e.getButton() == MouseEvent.BUTTON1))
      {
        isDeleteOp = false;
        PresentationLayer currentLayer = getModel().getElementAt(index);
        if (cellRender.isCheckbox(e.getPoint()))
        {
          currentLayer.setHidden(!currentLayer.isHidden());
          repaint(getCellBounds(index, index));
        }
      }
      else if (e.getButton() == MouseEvent.BUTTON3)
      {
        deleteLayer(index);
        if (index == (getModel().getSize()))
        {
          setSelectedIndex(index - 1);
        }
        else
        {
          setSelectedIndex(index);
        }
      }
    }
  }

  @Override
  public void mousePressed(MouseEvent e)
  {
  }

  @Override
  public void mouseReleased(MouseEvent e)
  {

  }

  @Override
  public void mouseEntered(MouseEvent e)
  {

  }

  @Override
  public void mouseExited(MouseEvent e)
  {

  }

}
