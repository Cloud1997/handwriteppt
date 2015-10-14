package com.github.handwriteppt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.io.File;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

/**
 * @author zhongfan
 *
 */
public class DrawPad extends JFrame
{
  private int                  currentPageIndex = 0;
  private PagesList            pagesList;
  private LayerList            layersList;
  private JLabel               addNewPageLabel;
  private JLabel               addNewLayerLabel;
  private JLabel               showLabel;
  private JLabel               saveLabel;
  private JLabel               strokeLabel;
  private JLabel               blankLabel;
  private StrokeSpinner        stroke;
  private RubberLabel          rubberLabel;
  private JPanel               colorSelectorLabel;
  private String               saveFolder;
  private String               fileNameFormat   = "%s\\%d-%d.png";
  private static final DrawPad instance         = new DrawPad("Hand Write Board");
  private GridBagLayout        layout;
  private GridBagConstraints   cons;
  private JPanel               toolbarPanel     = new JPanel();
  private JPanel               pageListPanel    = new JPanel();
  private JPanel               layerListPanel   = new JPanel();

  {
    try
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        | UnsupportedLookAndFeelException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static final DrawPad getInstance()
  {
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
	  GridBagLayout gridBag=new GridBagLayout();
    toolbarPanel.setLayout(gridBag);
    pageListPanel.setLayout(gridBag);
    layerListPanel.setLayout(gridBag);
    addNewPageLabel = new NewPageLabel(new ImageIcon("res/AddPage.png"));
    addNewLayerLabel = new NewLayerLabel(new ImageIcon("res/AddLayer.png"));
    rubberLabel = new RubberLabel(new ImageIcon("res/Eraser.png"));
    showLabel = new ShowLabel(new ImageIcon("res/show.png"));
    saveLabel = new SaveLabel(new ImageIcon("res/save.png"));
    colorSelectorLabel = new ColorChoosePanel(new ImageIcon("res/Paint.png"));
    strokeLabel = new JLabel(new ImageIcon("res/Pen.png"));
    strokeLabel.setBorder(new LineBorder(Color.black));
    stroke = new StrokeSpinner(1.0, 1.0, 20.0, 0.1);
    stroke.setPreferredSize(new Dimension(100, 1));
    stroke.setBorder(new LineBorder(Color.black));
    stroke.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
    JPanel strokeSpinnerPanel=new JPanel();
    strokeSpinnerPanel.setBorder(new LineBorder(Color.black));
    strokeSpinnerPanel.setLayout(new GridBagLayout());
    
    GridBagConstraints gc=GridBagLayoutUtil.getDefaultConstraints();
    gc.anchor=GridBagConstraints.EAST;
    gc.fill=GridBagConstraints.VERTICAL;
    strokeSpinnerPanel.add(strokeLabel,gc);
    gc.gridx=1;
    gc.anchor=GridBagConstraints.WEST;
    strokeSpinnerPanel.add(stroke,gc);
    blankLabel = new JLabel();
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
    gc=GridBagLayoutUtil.getDefaultConstraints();
    toolbarPanel.setBorder(new LineBorder(Color.black));
    toolbarPanel.add(saveLabel,gc);
    gc.gridx=1;
    toolbarPanel.add(showLabel,gc);
    gc.gridx=2;
    toolbarPanel.add(rubberLabel,gc);
    gc.gridx=3;
    toolbarPanel.add(colorSelectorLabel,gc);
    gc.gridx=4;
//    toolbarPanel.add(strokeLabel);
    toolbarPanel.add(strokeSpinnerPanel,gc);
    
    
    
    pageListPanel.setBorder(new LineBorder(Color.black));
    
     gc=GridBagLayoutUtil.getDefaultConstraints();
     gc.weighty=0;
     gc.anchor=GridBagConstraints.NORTH;
     gc.insets=new Insets(0, 10, 0, 5);
    pageListPanel.add(addNewPageLabel,gc);
    gc.gridy=1;
    gc.weighty=1;
    pageListPanel.add(pagesList,gc);
    
    
    gc=GridBagLayoutUtil.getDefaultConstraints();
    gc.weighty=0;
    gc.anchor=GridBagConstraints.NORTH;
    layerListPanel.setBorder(new LineBorder(Color.black));
    layerListPanel.add(addNewLayerLabel,gc);
    gc.gridy=1;
    gc.weighty=1;
    layerListPanel.add(layersList,gc);

    add(toolbarPanel, BorderLayout.NORTH);
    add(pageListPanel, BorderLayout.WEST);
    add(layerListPanel, BorderLayout.EAST);
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
  
  public void changePenColor(Color c){
	  getSelectedLayer().changePenColor(c);
  }

  public float getCurrentStroke()
  {
    return ((Double)stroke.getValue()).floatValue();
  }

  public boolean isRubberMode()
  {
    return rubberLabel.isRubberMode();
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
    else
    {
      File[] files = materialFolder.listFiles();
      for (File file : files)
      {
        if (file.isFile())
        {
          file.delete();
        }
      }
    }
  }

}
