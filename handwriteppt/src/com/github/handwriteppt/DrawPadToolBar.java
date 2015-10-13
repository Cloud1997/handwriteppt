package com.github.handwriteppt;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class DrawPadToolBar extends JToolBar
{
  private static String strokeLabelName = "Stroke";
  private JButton       addNewPageBtn;
  private JButton       addNewLayerBtn;
  private RubberBtn     drawRubberBtn;

  public RubberBtn getDrawRubberBtn()
  {
    return drawRubberBtn;
  }

  private ActionListener al = null;
  private JLabel         strokeLabel;
  private StrokeSpinner  stroke;

  public StrokeSpinner getStroke()
  {
    return stroke;
  }

  private JFrame parent;

  public DrawPadToolBar()
  {
    super();
    initialToolBar();
  }

  public DrawPadToolBar(JFrame parent)
  {
    super();
    this.parent = parent;
    initialToolBar();
  }

  public DrawPadToolBar(int orientation)
  {
    super(orientation);
    initialToolBar();
  }

  public DrawPadToolBar(String name, int orientation)
  {
    super(name, orientation);
    initialToolBar();
  }

  public DrawPadToolBar(String name)
  {
    super(name);
    initialToolBar();
  }

  private void initialToolBar()
  {
    initialToolBarComponents();
    add(addNewPageBtn);
    add(addNewLayerBtn);
    add(drawRubberBtn);
    add(strokeLabel);
    add(stroke);
    setSize(getPreferredSize());
  }

  private void initialToolBarComponents()
  {
    drawRubberBtn = new RubberBtn(ConsantList.RUBBER, parent);
    addNewPageBtn = new NewPageBtn(ConsantList.ADD_NEW_PAGE, parent);
    addNewLayerBtn = new NewLayerBtn(ConsantList.ADD_NEW_LAYER, parent);
    strokeLabel = new JLabel(strokeLabelName);
    stroke = new StrokeSpinner(1.0, 1.0, 20.0, 0.1);
    if (al != null)
    {
      drawRubberBtn.addActionListener(al);
    }
  }

}
