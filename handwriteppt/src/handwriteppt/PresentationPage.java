package handwriteppt;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;

public class PresentationPage extends JLayeredPane
{
  private List<PresentationLayer> layers    = new ArrayList<PresentationLayer>();
  private static int              testIndex = 0;
  private String                  name;

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public PresentationPage(String name)
  {
    super();
    this.name = name;
    layers.add(new PresentationLayer(name));
  }

  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    for (PresentationLayer layer : layers)
    {
      if (!layer.isHidden())
      {
        layer.paint(g);
      }
    }
  }

  public PresentationLayer getLayerByIndex(int index)
  {
    return layers.get(index);
  }

  public PresentationLayer getContentPane()
  {
    return layers.get(0);
  }

  @Override
  public String toString()
  {
    // TODO Auto-generated method stub
    return name;
  }

}
