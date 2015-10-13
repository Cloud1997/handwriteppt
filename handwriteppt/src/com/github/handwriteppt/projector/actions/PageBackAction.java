package com.github.handwriteppt.projector.actions;

import com.github.handwriteppt.Action;
import com.github.handwriteppt.projector.Projector;

public class PageBackAction implements Action {

	@Override
	public void act(Object... params) {
		// TODO Auto-generated method stub
		Projector.getInstance().pageBack();
	}

}
