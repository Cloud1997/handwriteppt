package com.github.handwriteppt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class ColorChoosePanel extends JPanel{
	private JLabel colorChooser;
	private JLabel currentColor=new JLabel();
	private ColorWindow win;
	private Color[] colors=new Color[]{Color.BLACK,Color.BLUE,Color.green,Color.red,Color.GRAY};
	public ColorChoosePanel(Icon icon){
		setLayout(new GridBagLayout());
		GridBagConstraints gc=	GridBagLayoutUtil.getDefaultConstraints();
		
		win=new ColorWindow(DrawPad.getInstance()){

			@Override
			public void colorChoosed(Color c) {
				currentColor.setBackground(c);
				DrawPad.getInstance().changePenColor(c);
			}};
	
		Dimension size=ScreenUtil.getScreenSize();
		win.setBounds(size.width/2-150, 100, 300, 150);
		colorChooser=new ClickableLabel(icon){

			@Override
			public void mouseClicked() {
				win.pop();
			}
			
		};

		add(colorChooser,gc);
		currentColor.setOpaque(true);
		currentColor.setBackground(Color.black);
		currentColor.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		currentColor.setPreferredSize(new Dimension(30, 30));
		gc.gridx=1;
		gc.insets=new Insets(0, 10, 0, 0);
		gc.fill=GridBagConstraints.VERTICAL;
		add(currentColor,gc);
		
	}
	
	



}
