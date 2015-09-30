package com.github.handwriteppt;

import java.awt.Component;
import java.awt.GridLayout;
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
  private JFrame parent;

  public LayerList(JFrame parent)
  {
    super(new DefaultListModel<PresentationLayer>());
    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.parent = parent;
    getModel().addElement(new PresentationLayer("Layer1"));
    getModel().addElement(new PresentationLayer("Layer2"));
    setCellRenderer(new LayerListRender());
    addMouseListener(this);
  }

  @Override
  public DefaultListModel<PresentationLayer> getModel()
  {
    return (DefaultListModel)super.getModel();
  }

  public class LayerListRender extends JPanel
      implements ListCellRenderer<PresentationLayer>
  {
    private JLabel    layerName;
    private JCheckBox showLayer;

    public LayerListRender()
    {
      super();
      setLayout(new GridLayout(0, 2));
      add(layerName = new JLabel());
      add(showLayer = new JCheckBox());
    }

    @Override
    public Component getListCellRendererComponent(JList< ? extends PresentationLayer> list,
        PresentationLayer value, int index, boolean isSelected, boolean cellHasFocus)
    {
      showLayer.setSelected(!value.isHidden());
      layerName.setText(value.getName());
      layerName.setFont(list.getFont());
      setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
      setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
      return this;
    }

  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    System.out.println(e.getSource());
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
