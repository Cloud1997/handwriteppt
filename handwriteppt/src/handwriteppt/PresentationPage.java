package handwriteppt;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;

public class PresentationPage extends JLayeredPane
{
  private List<PresentationLayer> layers = new ArrayList<PresentationLayer>();

  public PresentationPage()
  {
    super();
    layers.add(new PresentationLayer("background"));
    add(layers.get(0), new Integer(-1));
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

}
