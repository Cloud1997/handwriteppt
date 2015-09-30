package com.github.handwriteppt;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class DrawPadToolBar extends JToolBar
{
  private static String  strokeLabelName = "Stroke";
  private JButton        addNewPageBtn;
  private JButton        drawLineBtn;
  private ActionListener al              = null;
  private JButton        rubberBtn;
  private JLabel         strokeLabel;
  private StrokeSpinner  stroke;
  private JFrame         parent;

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

  //  public DrawPadToolBar(ActionListener al)
  //  {
  //    super();
  //    this.al = al;
  //    initialToolBar();
  //  }

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
    add(drawLineBtn);
    add(rubberBtn);
    add(strokeLabel);
    add(stroke);
    setSize(getPreferredSize());
  }

  private void initialToolBarComponents()
  {
    drawLineBtn = new JButton(ActionCommandList.DRAW_LINE);
    rubberBtn = new JButton(ActionCommandList.RUBBER);
    addNewPageBtn = new NewPageBtn(ActionCommandList.ADD_NEW_PAGE, parent);
    strokeLabel = new JLabel(strokeLabelName);
    stroke = new StrokeSpinner(1.0, 1.0, 20.0, 0.1);
    if (al != null)
    {
      drawLineBtn.addActionListener(al);
      rubberBtn.addActionListener(al);
    }
  }

}
