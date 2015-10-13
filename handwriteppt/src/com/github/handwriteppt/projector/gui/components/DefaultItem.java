package com.github.handwriteppt.projector.gui.components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JLabel;

import com.github.handwriteppt.Action;
import com.github.handwriteppt.Item;

public class DefaultItem extends JLabel implements Item {

	protected Action action;
//	protected Icon icon;
	
	public DefaultItem(Icon icon){
//		this.icon=icon;
		setIcon(icon);
	}
	public DefaultItem(Icon icon,Action action){
		this(icon);
		this.action=action;
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				action.act(new Object[]{null} );
			}
		});
	}
	

	@Override
	public void bindAction(Action a) {
		action=a;

	}

	@Override
	public void run() {
		action.act(new Object[]{null});
	}

}
