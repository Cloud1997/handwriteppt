package handwriteppt;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ActionSpinner extends JSpinner
    implements ChangeListener
{

  public ActionSpinner()
  {
    super();
    getModel().addChangeListener(this);
  }

  public ActionSpinner(SpinnerModel model)
  {
    super(model);
    getModel().addChangeListener(this);
  }

  @Override
  public void stateChanged(ChangeEvent e)
  {
  }

}
