package handwriteppt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ActionButton extends JButton
    implements ActionListener
{
  protected JFrame parent;

  public ActionButton(JFrame parent)
  {
    super();
    this.parent = parent;
    addActionListener(this);
  }

  public ActionButton(Action a, JFrame parent)
  {
    super(a);
    this.parent = parent;
    addActionListener(this);
  }

  public ActionButton(Icon icon, JFrame parent)
  {
    super(icon);
    this.parent = parent;
    addActionListener(this);
  }

  public ActionButton(String text, Icon icon, JFrame parent)
  {
    super(text, icon);
    this.parent = parent;
    addActionListener(this);
  }

  public ActionButton(String text, JFrame parent)
  {
    super(text);
    this.parent = parent;
    addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {

  }

}
