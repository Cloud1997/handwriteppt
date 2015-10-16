package com.github.handwriteppt.projector.gui.components;

import java.awt.Dimension;

import javax.swing.Icon;

import com.github.handwriteppt.Action;
import com.github.handwriteppt.ClickableLabel;
import com.github.handwriteppt.Item;

public class DefaultItem extends ClickableLabel implements Item {

	protected Action action;
	
	public DefaultItem(Icon icon){
		super(icon);
	}
	public DefaultItem(Icon icon,final Action action){
		this(icon);
		this.action=action;
		setPreferredSize(new Dimension(55, 55));
	}
	

	@Override
	public void bindAction(Action a) {
		action=a;

	}

	@Override
	public void run() {
		action.act(new Object[]{null});
	}
	@Override
	public void mouseClicked() {
		run();
		
	}

}
