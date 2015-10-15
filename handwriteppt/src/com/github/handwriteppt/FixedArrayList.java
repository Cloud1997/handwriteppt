package com.github.handwriteppt;

import java.util.ArrayList;

public class FixedArrayList<E> extends ArrayList<E>
{
  private int maxSize;

  public FixedArrayList(int maxSize)
  {
    super();
    this.maxSize = maxSize;
  }

  @Override
  public boolean add(E e)
  {
    if (size() == maxSize)
    {
      remove(0);
    }
    return super.add(e);

  }

}
