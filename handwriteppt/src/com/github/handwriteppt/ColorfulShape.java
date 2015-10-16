package com.github.handwriteppt;

import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;

public class ColorfulShape
{
  private Shape shape;

  public Shape getShape()
  {
    return shape;
  }

  public void setShape(Shape shape)
  {
    this.shape = shape;
  }

  private Stroke stroke;

  public Stroke getStroke()
  {
    return stroke;
  }

  public void setStroke(Stroke stroke)
  {
    this.stroke = stroke;
  }

  public Color getColor()
  {
    return color;
  }

  public void setColor(Color color)
  {
    this.color = color;
  }

  private Color color;

  public ColorfulShape(Shape shape, Stroke stroke, Color color)
  {
    this.shape = shape;
    this.stroke = stroke;
    this.color = color;
  }

}
