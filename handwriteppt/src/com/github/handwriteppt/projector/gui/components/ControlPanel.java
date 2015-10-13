package com.github.handwriteppt.projector.gui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.github.handwriteppt.Action;
import com.github.handwriteppt.Item;
import com.github.handwriteppt.projector.actions.PageBackAction;
import com.github.handwriteppt.projector.actions.PageForwardAction;
import com.github.handwriteppt.projector.actions.QuitAction;

public class ControlPanel {
	
	private JPanel panel;
	private GridBagConstraints gc=new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(30, 0, 0, 0), 0, 0);
	
	public ControlPanel(){
		initUI();
	}
	private void initUI(){
		panel=new JPanel();
		panel.setLayout(new GridBagLayout());
//		Icon icon=new ImageIcon("res/previous_page.png");
//		Action action=new PageBackAction();
//		addItem(new DefaultItem(icon,action));
//		icon=new ImageIcon("res/next_page.png"); 
//		action=new PageForwardAction();
//		addItem(new DefaultItem(icon,action));
//		PenColorChangeItem it=	new PenColorChangeItem();
////		it.setBounds(0, 0, 100, 500);
//		addItem(it);
//		
//		icon=new ImageIcon("res/quit.png"); 
//		action=new QuitAction();
//		
//		panel.setLayout(null);
		PenColorChangeItem it=		new PenColorChangeItem();
		it.setBounds(400, 0, 30, 20);
		panel.add(it,gc);
//		addItem(new DefaultItem(icon,action));
		panel.setPreferredSize(new Dimension(100, 100));
		panel.setBorder(new LineBorder(Color.blue));
	}
	private void addItem(Item it){
		gc.gridx=gc.gridx+1;
		panel.add((Component)it, gc);
	}
	
	public JPanel getPanel(){
		return panel;
	}
}
