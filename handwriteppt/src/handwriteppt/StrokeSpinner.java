package handwriteppt;

import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;

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

  @Override
  public void stateChanged(ChangeEvent e)
  {
    SpinnerModel source = (SpinnerModel)e.getSource();
    System.out.println("The value is: " + source.getValue());
  }

}
