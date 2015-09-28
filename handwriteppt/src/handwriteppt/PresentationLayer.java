package handwriteppt;

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
    isHidden = false;
  }

  @Override
  public void paint(Graphics g)
  {
    g.drawString("PresentationLayer", 500, 500);
    System.out.println("PresentationLayer");
    super.paint(g);
  }

}
