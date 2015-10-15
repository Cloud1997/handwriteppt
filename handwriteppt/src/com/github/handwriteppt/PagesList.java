package com.github.handwriteppt;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PagesList extends JList<PresentationPage>
    implements ListSelectionListener, MouseListener
{
  private boolean isDeleteOpStarted = false;
  private int     currentPageIndex  = 0;

  public int getCurrentPageIndex()
  {
    return currentPageIndex;
  }

  public void setCurrentPageIndex(int currentPageIndex)
  {
    this.currentPageIndex = currentPageIndex;
  }

  private DrawPad parent;

  public boolean isDeleteOpStarted()
  {
    return isDeleteOpStarted;
  }

  public void setDeleteOpStarted(boolean isDeleteOp)
  {
    this.isDeleteOpStarted = isDeleteOp;
  }

  public PagesList(DrawPad parent)
  {
    super(new DefaultListModel<PresentationPage>());
    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.parent = parent;
    addListSelectionListener(this);
    addMouseListener(this);
    setCellRenderer(new PageListCellRender());
  }

  @Override
  public DefaultListModel<PresentationPage> getModel()
  {
    return (DefaultListModel<PresentationPage>)super.getModel();
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    int currentIndex = locationToIndex(e.getPoint());
    if ((e.getButton() == MouseEvent.BUTTON3))
    {
      deleteOnePage(currentIndex);
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

  }

  @Override
  public void mouseReleased(MouseEvent e)
  {

  }

  @Override
  public void mouseEntered(MouseEvent e)
  {

  }

  @Override
  public void mouseExited(MouseEvent e)
  {

  }

  @Override
  public void valueChanged(ListSelectionEvent e)
  {
    if (!isDeleteOpStarted)
    {
      getModel().getElementAt(currentPageIndex).setVisible(false);
      currentPageIndex = getSelectedIndex();
      getModel().getElementAt(currentPageIndex).setVisible(true);
      parent.switchPage(currentPageIndex);
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
      parent.addNewPage();
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
        getModel().getElementAt(i).setName(String.format(ConsantList.PAGE_NAME_FORMATE, i + 1));
      }
    }
    isDeleteOpStarted = false;
    setSelectedIndex(currentPageIndex);
  }

  private class PageListCellRender extends DefaultListCellRenderer
  {

    @Override
    public Component getListCellRendererComponent(JList< ? > list, Object value, int index,
        boolean isSelected, boolean cellHasFocus)
    {
      setIcon(new ImageIcon("res/Page.png"));
      setText(value.toString());
      setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
      setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
      return this;
    }

  }

}
