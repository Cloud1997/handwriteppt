package handwriteppt;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class DrawPad extends JFrame
    implements ActionListener
{
  private DrawPadToolBar         drawToolbar;
  private List<PresentationPage> presentationPages = new ArrayList<PresentationPage>();
  private int                    currentPageIndex  = 0;

  public int getCurrentPageIndex()
  {
    return currentPageIndex;
  }

  public void setCurrentPageIndex(int currentPageIndex)
  {
    this.currentPageIndex = currentPageIndex;
  }

  public DrawPad(String title) throws HeadlessException
  {
    super(title);
    initialUiComps();
  }

  private void initialUiComps()
  {
    drawToolbar = new DrawPadToolBar(this);
    loadPagesFromFile("");
    add(drawToolbar, BorderLayout.NORTH);
    //presentationPages.add(new PresentationPage());
    if (!presentationPages.isEmpty())
    {
      //add(presentationPages.get(currentPageIndex), BorderLayout.CENTER);
      setLayeredPane(presentationPages.get(currentPageIndex));
    }
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

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (e.getActionCommand().equals(ActionCommandList.ADD_NEW_PAGE))
    {
      //      if (!presentationPages.isEmpty())
      //      {
      //        remove(presentationPages.get(currentPageIndex));
      //      }
      PresentationPage addedPage = new PresentationPage();
      presentationPages.add(addedPage);
      currentPageIndex = presentationPages.size() - 1;
      setLayeredPane(addedPage);
      //add(addedPage, BorderLayout.CENTER, 0);

    }
  }

}
