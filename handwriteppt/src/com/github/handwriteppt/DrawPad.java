package com.github.handwriteppt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import com.github.handwriteppt.projector.Projector;

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

  private JLabel               saveLabel;
  private JLabel               strokeLabel;

  private StrokeSpinner        stroke;
  private RubberLabel          rubberLabel;
  private JPanel               colorSelectorLabel;
  private String               saveFolder;
  private String               fileNameFormat   = "%s\\%d-%d.png";
  private static final DrawPad instance         = new DrawPad("Hand Write Board");

  private JPanel               toolbarPanel     = new JPanel();
  private JPanel               pageListPanel    = new JPanel();
  private JPanel               layerListPanel   = new JPanel();
  private JPanel               savePanel        = new JPanel();
  private JTextField           savedFileName;
  private JLabel               fileList;
  private LineBorder           border           = new LineBorder(Color.black);
  private Font                 font             = new Font(Font.SANS_SERIF, Font.BOLD, 32);
  private String               zipExt           = ".zip";
  private JPanel               showPanel;

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
    GridBagLayout gridBag = new GridBagLayout();
    toolbarPanel.setLayout(gridBag);
    pageListPanel.setLayout(gridBag);
    layerListPanel.setLayout(gridBag);
    savedFileName = new JTextField();
    savedFileName.setText("material.zip");
    savedFileName.setFont(font);
//    savedFileName.setBorder(border);
    fileList = new MaterialList(new ImageIcon("res/ShowFile.png"));
//    fileList.setBorder(border);
    addNewPageLabel = new NewPageLabel(new ImageIcon("res/AddPage.png"));
    addNewLayerLabel = new NewLayerLabel(new ImageIcon("res/AddLayer.png"));
    rubberLabel = new RubberLabel(new ImageIcon("res/Eraser.png"),new ImageIcon("res/PinkEraser.png"));

    saveLabel = new SaveLabel(new ImageIcon("res/save.png"));
//    saveLabel.setBorder(border);
    colorSelectorLabel = new ColorChoosePanel(new ImageIcon("res/Paint.png"));
    strokeLabel = new JLabel(new ImageIcon("res/Pen.png"));
//    strokeLabel.setBorder(border);
    stroke = new StrokeSpinner(1.0, 1.0, 20.0, 0.1);
    stroke.setPreferredSize(new Dimension(100, 1));
//    stroke.setBorder(border);
    stroke.setFont(font);
    JPanel strokeSpinnerPanel = new JPanel();
//    strokeSpinnerPanel.setBorder(border);
    strokeSpinnerPanel.setLayout(new GridBagLayout());

    GridBagConstraints gc = GridBagLayoutUtil.getDefaultConstraints();
    gc.anchor = GridBagConstraints.EAST;
    gc.fill = GridBagConstraints.VERTICAL;
    strokeSpinnerPanel.add(strokeLabel, gc);
    gc.gridx = 1;
    gc.anchor = GridBagConstraints.WEST;
    strokeSpinnerPanel.add(stroke, gc);
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
    gc = GridBagLayoutUtil.getDefaultConstraints();
    gc.anchor = GridBagConstraints.EAST;
    gc.fill = GridBagConstraints.VERTICAL;
    savePanel.setLayout(new GridBagLayout());
    savePanel.add(saveLabel, gc);
    gc.gridx = 1;
    gc.anchor = GridBagConstraints.WEST;
    savedFileName.setColumns(7);
    savePanel.add(savedFileName, gc);
//    savePanel.setBorder(border);
    gc = GridBagLayoutUtil.getDefaultConstraints();
    toolbarPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
    toolbarPanel.add(savePanel, gc);
    gc.gridx = 2;
    showPanel = new ShowMaterialPanel();
    toolbarPanel.add(showPanel, gc);
    gc.gridx = 3;
    toolbarPanel.add(rubberLabel, gc);
    gc.gridx = 4;
    toolbarPanel.add(colorSelectorLabel, gc);
    gc.gridx = 5;
    //    toolbarPanel.add(strokeLabel);
    toolbarPanel.add(strokeSpinnerPanel, gc);

    pageListPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));

    gc = GridBagLayoutUtil.getDefaultConstraints();
    gc.weighty = 0;
    gc.anchor = GridBagConstraints.NORTH;
    gc.insets = new Insets(0, 10, 0, 5);
    pageListPanel.add(addNewPageLabel, gc);
    gc.gridy = 1;
    gc.weighty = 1;
    pageListPanel.add(pagesList, gc);

    gc = GridBagLayoutUtil.getDefaultConstraints();
    gc.weighty = 0;
    gc.anchor = GridBagConstraints.NORTH;
    layerListPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black));
    layerListPanel.add(addNewLayerLabel, gc);
    gc.gridy = 1;
    gc.weighty = 1;
    layerListPanel.add(layersList, gc);

    add(toolbarPanel, BorderLayout.NORTH);
    add(pageListPanel, BorderLayout.WEST);
    add(layerListPanel, BorderLayout.EAST);

    
    
    ScreenUtil.setCursor(ScreenUtil.PEN);
    
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    validate();
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
    layersList.setSelectedIndex(0);
  }

  public void addNewLayerToCurrentPage()
  {
    pagesList.getModel().getElementAt(pagesList.getCurrentPageIndex()).addNewLayer();
    layersList.setSelectedIndex(0);
  }

  public PresentationPage getSelectedPage()
  {
    return pagesList.getSelectedValue();
  }

  public PresentationLayer getSelectedLayer()
  {
    return layersList.getSelectedValue();
  }

  public void changePenColor(Color c)
  {
    getSelectedLayer().changePenColor(c);
  }

  public float getCurrentStroke()
  {
    String strokeStr = ((NumberEditor)stroke.getEditor()).getTextField().getText();
    try
    {
      float strokeVal = Float.valueOf(strokeStr);
      if (strokeVal > 20.0)
      {
        strokeVal = 20.0f;
      }
      stroke.setValue(strokeVal);
    }
    catch (Exception e)
    {
      stroke.setValue(stroke.getValue());
    }
    return (float)stroke.getValue();
  }

  public boolean isRubberMode()
  {
    return rubberLabel.isRubberMode();
  }

  public void showMaterial(String fileName)
  {
    Properties properties = System.getProperties();
    String currentFolder = properties.getProperty("user.dir");
    Projector.show(currentFolder + "\\" + fileName);
  }

  public void saveMaterial(String destFilePath)
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
      ZipUtils.compress(saveFolder, destFilePath);
    }
    catch (Exception e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public String getCurrentFileName()
  {
    Properties properties = System.getProperties();
    String currentFolder = properties.getProperty("user.dir");
    if (!savedFileName.getText().endsWith(zipExt))
    {
      return currentFolder + "\\" + savedFileName.getText() + zipExt;
    }
    else
    {
      return currentFolder + "\\" + savedFileName.getText();
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

  public List<String> getMaterialList()
  {
    List<String> fileList = new ArrayList<String>();
    Properties properties = System.getProperties();
    File currentFolder = new File(properties.getProperty("user.dir"));
    File[] childrenFiles = currentFolder.listFiles();
    for (File file : childrenFiles)
    {
      if (file.isFile())
      {
        String fileName = file.getName();
        if (fileName.endsWith(zipExt))
        {
          fileList.add(fileName);
        }
      }
    }
    return fileList;
  }

  public Point getFileListPos()
  {
    Rectangle rect = showPanel.getBounds();
    return new Point(rect.x + 17, rect.y + rect.height + 17);
  }

}
