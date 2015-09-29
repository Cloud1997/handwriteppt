package handwriteppt;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

/**
 * @author zhongfan
 *
 */
public class DrawPad extends JFrame
{
  private DrawPadToolBar drawToolbar;
  private int            currentPageIndex = 0;
  private PagesList      pagesList;

  public int getCurrentPageIndex()
  {
    return currentPageIndex;
  }

  public void setCurrentPageIndex(int currentPageIndex)
  {
    this.currentPageIndex = currentPageIndex;
  }

  public int getPagesCount()
  {
    return pagesList.getModel().getSize();
  }

  public DrawPad(String title) throws HeadlessException
  {
    super(title);
    initialUiComps();
  }

  private void initialUiComps()
  {
    drawToolbar = new DrawPadToolBar(this);
    pagesList = new PagesList(this);
    //pagesList.addListSelectionListener(this);
    //    pagesList.addMouseListener(this);
    loadPagesFromFile("");
    if (pagesList.getModel().getSize() != 0)
    {
      add(pagesList.getModel().getElementAt(currentPageIndex), BorderLayout.CENTER);
    }
    else
    {
      addNewPage();
    }
    add(drawToolbar, BorderLayout.NORTH);
    add(pagesList, BorderLayout.WEST);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setVisible(true);
    validate();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void loadPagesFromFile(String filePath)
  {

  }

  public static void main(String[] args)
  {
    DrawPad drawpad = new DrawPad("test");
  }

  public void addNewPage()
  {
    PresentationPage addedPage = new PresentationPage(
        String.format(PagesList.PAGE_NAME_FORMATE, pagesList.getModel().size() + 1));
    pagesList.getModel().addElement(addedPage);
    add(addedPage, BorderLayout.CENTER);
    pagesList.setSelectedIndex(pagesList.getModel().size() - 1);
  }

}
