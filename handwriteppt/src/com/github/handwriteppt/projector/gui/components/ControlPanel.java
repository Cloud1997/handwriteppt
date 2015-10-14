package com.github.handwriteppt.projector.gui.components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.github.handwriteppt.Action;
import com.github.handwriteppt.Item;
import com.github.handwriteppt.projector.actions.PageBackAction;
import com.github.handwriteppt.projector.actions.PageForwardAction;
import com.github.handwriteppt.projector.actions.QuitAction;

public class ControlPanel {
	
	private JPanel panel;
	private JPanel innerPanel;
	private GridBagConstraints gc=new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
	
	public ControlPanel(){
		initUI();
	}
	private void initUI(){
		panel=new JPanel();
		innerPanel=new JPanel();
		innerPanel.setVisible(false);
		innerPanel.setLayout(new GridBagLayout());
		panel.setLayout(new GridBagLayout());
		panel.add(innerPanel,gc);
		gc.fill=GridBagConstraints.NONE;
		Icon icon=new ImageIcon("res/previous_page.png");
		Action action=new PageBackAction();
		addItem(new DefaultItem(icon,action));
		icon=new ImageIcon("res/next_page.png"); 
		action=new PageForwardAction();
		addItem(new DefaultItem(icon,action));
		PenColorChangeItem it=	new PenColorChangeItem();
		addItem(it);
		
		icon=new ImageIcon("res/quit.png"); 
		action=new QuitAction();
		addItem(new DefaultItem(icon,action));
		panel.setPreferredSize(new Dimension(100, 60));
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if(e.getY()<0)
				{
					visible(false);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				visible(true);
			}
		});
	}
	private void addItem(Item it){
		gc.gridx=gc.gridx+1;
		innerPanel.add((Component)it, gc);
	}
	
	public JPanel getPanel(){
		return panel;
	}
	
	private void visible(boolean v){
		innerPanel.setVisible(v);
	}
}
