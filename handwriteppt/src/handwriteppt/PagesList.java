package handwriteppt;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PagesList extends JList<PresentationPage>
    implements ListSelectionListener, MouseListener
{
  private boolean            isDeleteOpStarted = false;
  private int                currentPageIndex  = 0;
  private JFrame             parent;
  public static final String PAGE_NAME_FORMATE = "Page%d";

  public boolean isDeleteOpStarted()
  {
    return isDeleteOpStarted;
  }

  public void setDeleteOpStarted(boolean isDeleteOp)
  {
    this.isDeleteOpStarted = isDeleteOp;
  }

  public PagesList(JFrame parent)
  {
    super(new DefaultListModel<PresentationPage>());
    this.parent = parent;
    addListSelectionListener(this);
    addMouseListener(this);
    // TODO Auto-generated constructor stub
  }

  @Override
  public DefaultListModel<PresentationPage> getModel()
  {
    // TODO Auto-generated method stub
    return (DefaultListModel<PresentationPage>)super.getModel();
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    if ((e.getButton() == MouseEvent.BUTTON3))
    {
      deleteOnePage(locationToIndex(e.getPoint()));
    }
    else if (e.getButton() == MouseEvent.BUTTON1)
    {
      isDeleteOpStarted = false;
    }
    else
    {
    }

  }

  @Override
  public void mousePressed(MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseReleased(MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseEntered(MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void valueChanged(ListSelectionEvent e)
  {
    if (!isDeleteOpStarted)
    {
      getModel().getElementAt(currentPageIndex).setVisible(false);
      currentPageIndex = getSelectedIndex();
      getModel().getElementAt(currentPageIndex).setVisible(true);
    }
  }

  private void deleteOnePage(int indexToBeRemoved)
  {
    setSelectedIndex(indexToBeRemoved);
    isDeleteOpStarted = true;
    getModel().getElementAt(indexToBeRemoved).setVisible(false);
    getModel().removeElementAt(indexToBeRemoved);
    if (getModel().size() == 0)
    {
      ((DrawPad)parent).addNewPage();
    }
    else if (indexToBeRemoved == getModel().size())
    {
      currentPageIndex = indexToBeRemoved - 1;
    }
    else
    {
      currentPageIndex = indexToBeRemoved;
      for (int i = indexToBeRemoved; i < getModel().size(); i++)
      {
        getModel().getElementAt(i).setName(String.format(PAGE_NAME_FORMATE, i + 1));
      }
    }
    isDeleteOpStarted = false;
    setSelectedIndex(currentPageIndex);
  }

}
