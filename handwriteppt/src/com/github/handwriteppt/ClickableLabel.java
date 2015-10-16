package com.github.handwriteppt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public abstract class ClickableLabel extends JLabel {

	protected static Border upBoader = BorderFactory.createBevelBorder(BevelBorder.RAISED);
	protected static Border downBoader = BorderFactory.createBevelBorder(BevelBorder.LOWERED);

	public ClickableLabel(Icon image) {
		super(image);
		setPreferredSize(new Dimension(image.getIconWidth() + 5, image.getIconHeight() + 5));
		addMouseListener(new DynamicBorderMouseListener());
	}

	public ClickableLabel(String text) {

		super(text);
		setPreferredSize(new Dimension(getPreferredSize().width + 5, getPreferredSize().height + 5));
		addMouseListener(new DynamicBorderMouseListener());

	}



	private class DynamicBorderMouseListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				ClickableLabel.this.mousePressed();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				ClickableLabel.this.mouseReleased();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			ClickableLabel.this.mouseEntered();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			ClickableLabel.this.mouseExited();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				ClickableLabel.this.mouseClicked();
			}
		}

	}
	
	protected void mousePressed() {
		setBorder(downBoader);

	}

	protected abstract void mouseClicked();

	protected void mouseExited() {
		setBorder(null);
	}

	protected void mouseEntered() {
		setBorder(upBoader);
	}

	protected void mouseReleased() {
		setBorder(upBoader);
	}

}
