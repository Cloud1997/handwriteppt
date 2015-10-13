package com.github.handwriteppt;

import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class StrokeSpinner extends ActionSpinner
{

  public StrokeSpinner()
  {
    super();
  }

  public StrokeSpinner(int defautVal, int minVal, int maxVal, int stepVal)
  {
    super(new SpinnerNumberModel(defautVal, minVal, maxVal, stepVal));
  }

  public StrokeSpinner(double defautVal, double minVal, double maxVal, double stepVal)
  {
    super(new SpinnerNumberModel(defautVal, minVal, maxVal, stepVal));
  }

  public StrokeSpinner(SpinnerModel model)
  {
    super(model);
  }

}
