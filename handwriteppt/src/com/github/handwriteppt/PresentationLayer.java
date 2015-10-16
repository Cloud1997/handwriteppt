package com.github.handwriteppt;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PresentationLayer extends JPanel
{
  private String              name;
  private boolean             isHidden         = false;
  private ColorfulShape       newShape;
  private JFrame              parent;
  private BufferedImage       image;
  private Graphics2D          currentG2;
  private List<BufferedImage> historyImageList = new FixedArrayList<BufferedImage>(20);
  private static Color        currentColor     = Color.black;
  private List<ColorfulShape> shapeList        = new ArrayList<ColorfulShape>();

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
      image = new BufferedImage(getBounds().width, getBounds().height, BufferedImage.TYPE_INT_ARGB);
      currentG2 = image.createGraphics();
    }
    //    currentG2.setColor(currentColor);
    //    currentG2.setStroke(
    //        new BasicStroke(((DrawPad)parent).getCurrentStroke(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
    //    if (!historyImageList.isEmpty())
    //    {
    //      currentG2.drawImage(historyImageList.get(historyImageList.size() - 1), 0, 0, null);
    //    }
    //    historyImageList.add(image);
    if (!((DrawPad)parent).isRubberMode())
    {
      //      currentG2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
      newShape = new ColorfulShape(new GeneralPath(),
          new BasicStroke(((DrawPad)parent).getCurrentStroke(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL),
          currentColor);
      shapeList.add(newShape);
      ((GeneralPath)newShape.getShape()).moveTo(startPoint.getX(), startPoint.getY());
      //currentG2.draw(newShape);
    }
    else
    {
      //      currentG2.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
      int clearWidth = Math.round(((DrawPad)parent).getCurrentStroke()) + 20;
      newShape = new ColorfulShape(new Rectangle(startPoint.x, startPoint.y, clearWidth, clearWidth), null,
          null);
      shapeList.add(newShape);
    }
  }

  public void erase(Point startPoint)
  {
    int clearWidth = Math.round(((DrawPad)parent).getCurrentStroke()) + 20;
    //    currentG2.fillRect(startPoint.x, startPoint.y, clearWidth, clearWidth);
    Rectangle eraseRect = new Rectangle(startPoint.x, startPoint.y, clearWidth, clearWidth);
    shapeList.add(newShape = new ColorfulShape(eraseRect, null, null));
    //currentG2.fill(eraseRect);
  }

  public void drawLine(Point wayPoint)
  {
    ((GeneralPath)newShape.getShape()).lineTo(wayPoint.getX(), wayPoint.getY());
    //currentG2.draw(newShape);
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    //    if (!historyImageList.isEmpty())
    //    {
    //      g.drawImage(historyImageList.get(historyImageList.size() - 1), getBounds().x, getBounds().y,
    //          getBounds().width, getBounds().height, null);
    //    }
    Graphics2D g2 = (Graphics2D)g;
    if (currentG2 == null)
    {
      return;
    }

    currentG2.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
    currentG2.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
    if (!shapeList.isEmpty())
    {
      for (ColorfulShape shape : shapeList)
      {
        if (shape.getShape() instanceof Path2D)
        {
          currentG2.setColor(shape.getColor());
          currentG2.setStroke(shape.getStroke());
          currentG2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
          currentG2.draw(shape.getShape());
        }
        else if (shape.getShape() instanceof Rectangle)
        {
          currentG2.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
          Rectangle rect = (Rectangle)shape.getShape();
          //          //          g2.setStroke(new BasicStroke(((DrawPad)parent).getCurrentStroke() + 20, BasicStroke.CAP_BUTT,
          //          //              BasicStroke.JOIN_BEVEL));
          //          g2.setColor(ColorsUtil.getTransparentColor());
          currentG2.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
      }
    }
    g2.drawImage(image, 0, 0, null);
  }

  public void eraseEnd()
  {
    shapeList.add(new ColorfulShape(null, null, null));
  }

  public void undoLastDraw()
  {
    //    if (historyImageList.size() > 1)
    //    {
    //      historyImageList.remove(historyImageList.size() - 1);
    //    }
    if (!shapeList.isEmpty())
    {
      ColorfulShape undoShape = shapeList.get(shapeList.size() - 1);
      if (undoShape.getShape() instanceof Path2D)
      {
        shapeList.remove(undoShape);
      }
      else if ((undoShape.getShape() == null) || (undoShape.getShape() instanceof Rectangle))
      {
        shapeList.remove(undoShape);
        while (true)
        {
          if (shapeList.isEmpty())
          {
            break;
          }
          ColorfulShape nextUndoShape = shapeList.get(shapeList.size() - 1);
          if (nextUndoShape.getShape() == null)
          {
            shapeList.remove(nextUndoShape);
            break;
          }
          else if (nextUndoShape.getShape() instanceof Rectangle)
          {
            shapeList.remove(nextUndoShape);
          }
          else
          {
            break;
          }
        }
      }
    }
  }

  public void saveAsPng(String fileName)
  {
    File outputFile = new File(fileName);
    try
    {
      if (image == null)
      {
        ImageIO.write(new BufferedImage(ScreenUtil.getScreenSize().width, ScreenUtil.getScreenSize().height,
            BufferedImage.TYPE_INT_ARGB), "png", outputFile);
      }
      else
      {
        ImageIO.write(image, "png", outputFile);
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
