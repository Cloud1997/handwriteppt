package com.github.handwriteppt;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowMaterialPanel extends JPanel
{
  private JLabel      showLabel;
  private JLabel      fileChooserLabel;
  private FileChooser fileChooserWindow;

  public ShowMaterialPanel()
  {
    super();
    initUi();
  }

  private void initUi()
  {
    setLayout(new GridBagLayout());
    showLabel = new ShowLabel(new ImageIcon("res/show.png"));
    fileChooserLabel = new ClickableLabel(new ImageIcon("res/ShowFile.png")) {

      @Override
      public void mouseClicked()
      {
        fileChooserWindow.pop();
      }
    };
    fileChooserWindow = new FileChooser(DrawPad.getInstance());
    GridBagConstraints gc = GridBagLayoutUtil.getDefaultConstraints();
    add(showLabel, gc);
    gc.gridx = 1;
    add(fileChooserLabel, gc);
  }

}
