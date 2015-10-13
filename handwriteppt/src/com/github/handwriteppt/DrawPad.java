package com.github.handwriteppt;

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
  private LayerList      layersList;

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
    layersList = new LayerList(this);
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
    add(layersList, BorderLayout.EAST);
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
        String.format(ConsantList.PAGE_NAME_FORMATE, pagesList.getModel().size() + 1), this);
    pagesList.getModel().addElement(addedPage);
    pagesList.setSelectedIndex(pagesList.getModel().size() - 1);
    layersList.setModel(pagesList.getSelectedValue().getLayers());
    layersList.setSelectedIndex(0);
    add(addedPage, BorderLayout.CENTER);
  }

  public void switchPage(int index)
  {
    layersList.switchModel(pagesList.getModel().getElementAt(index).getLayers());
  }

  public void addNewLayerToCurrentPage()
  {
    pagesList.getModel().getElementAt(pagesList.getCurrentPageIndex()).addNewLayer();
    layersList.setSelectedIndex(layersList.getModel().size() - 1);
  }

  public PresentationPage getSelectedPage()
  {
    return pagesList.getSelectedValue();
  }

  public PresentationLayer getSelectedLayer()
  {
    return layersList.getSelectedValue();
  }

  public float getCurrentStroke()
  {
    return ((Double)drawToolbar.getStroke().getValue()).floatValue();
  }

  public boolean isRubberMode()
  {
    return drawToolbar.getDrawRubberBtn().isRubberMode();
  }

}
