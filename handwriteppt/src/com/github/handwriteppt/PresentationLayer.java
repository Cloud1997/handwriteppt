package com.github.handwriteppt;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PresentationLayer extends JPanel
{
  private String              name;
  private boolean             isHidden         = false;
  private Shape               newShape;
  private JFrame              parent;
  private BufferedImage       image;
  private Graphics2D          currentG2;
  private List<BufferedImage> historyImageList = new FixedArrayList<BufferedImage>(20);
  private static Color        currentColor     = Color.black;

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
    setVisible(!isHidden);
    parent.repaint();
  }

  public PresentationLayer(String name, boolean isOpaque, JFrame parent)
  {
    super();
    setOpaque(isOpaque);
    this.name = name;
    this.parent = parent;
  }

  public Graphics2D getGraphics2D()
  {
    return currentG2;
  }

  public void startToDraw(Point startPoint)
  {
    if (currentG2 == null)
    {
      setBounds(new Rectangle(ScreenUtil.getScreenSize()));
    }
    image = new BufferedImage(getBounds().width, getBounds().height, BufferedImage.TYPE_INT_ARGB);
    currentG2 = image.createGraphics();
    currentG2.setColor(currentColor);
    currentG2.setStroke(
        new BasicStroke(((DrawPad)parent).getCurrentStroke(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
    if (!historyImageList.isEmpty())
    {
      currentG2.drawImage(historyImageList.get(historyImageList.size() - 1), 0, 0, null);
    }
    historyImageList.add(image);
    if (!((DrawPad)parent).isRubberMode())
    {
      currentG2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
      newShape = new GeneralPath();
      ((GeneralPath)newShape).moveTo(startPoint.getX(), startPoint.getY());
      currentG2.draw(newShape);
    }
    else
    {
      currentG2.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
    }
  }

  public void erase(Point startPoint)
  {
    int clearWidth = Math.round(((DrawPad)parent).getCurrentStroke()) + 20;
    currentG2.fillRect(startPoint.x, startPoint.y, clearWidth, clearWidth);
  }

  public void drawLine(Point wayPoint)
  {
    ((GeneralPath)newShape).lineTo(wayPoint.getX(), wayPoint.getY());
    currentG2.draw(newShape);
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    if (!historyImageList.isEmpty())
    {
      g.drawImage(historyImageList.get(historyImageList.size() - 1), getBounds().x, getBounds().y,
          getBounds().width, getBounds().height, null);
    }
  }

  public void undoLastDraw()
  {
    if (historyImageList.size() > 1)
    {
      historyImageList.remove(historyImageList.size() - 1);
    }
  }

  public void saveAsPng(String fileName)
  {
    File outputFile = new File(fileName);
    try
    {
      if (historyImageList.isEmpty())
      {
        ImageIO.write(new BufferedImage(ScreenUtil.getScreenSize().width, ScreenUtil.getScreenSize().height,
            BufferedImage.TYPE_INT_ARGB), "png", outputFile);
      }
      else
      {
        ImageIO.write(historyImageList.get(historyImageList.size() - 1), "png", outputFile);
      }

    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void changePenColor(Color c)
  {
    currentColor = c;
  }

}
