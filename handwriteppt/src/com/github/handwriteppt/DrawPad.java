package com.github.handwriteppt;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.io.File;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
  private String         saveFolder;
  private String         fileNameFormat   = "%s\\%d-%d.png";
  private static final DrawPad instance=new DrawPad("Hand Write Board");
  {
	  try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
			| UnsupportedLookAndFeelException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  public static final JFrame getInstance(){
	  return instance;
  }
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

  private DrawPad(String title) throws HeadlessException
  {
    super(title);
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
	  instance.initialUiComps();
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

  public void saveMaterial()
  {
    createMaterialFolderIfNeeded();
    for (int i = 0; i < pagesList.getModel().size(); i++)
    {
      DefaultListModel<PresentationLayer> layers = pagesList.getModel().get(i).getLayers();
      for (int j = 0; j < layers.size(); j++)
      {
        String fileName = String.format(fileNameFormat, saveFolder, i + 1, j + 1);
        layers.getElementAt(layers.size() - 1 - j).saveAsPng(fileName);
      }
    }
    try
    {
      Properties properties = System.getProperties();
      ZipUtils.compress(saveFolder, properties.getProperty("user.dir") + "\\test.zip");
    }
    catch (Exception e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private void createMaterialFolderIfNeeded()
  {
    Properties properties = System.getProperties();
    saveFolder = properties.getProperty("user.dir") + "\\materials";
    File materialFolder = new File(saveFolder);
    if (!materialFolder.exists())
    {
      materialFolder.mkdir();
    }
  }

}
