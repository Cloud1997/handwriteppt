package com.github.handwriteppt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JWindow;

public class FileChooser extends JWindow {

	private DrawPad controller;
	private int width = 500;
	private Color bg = new Color(192,192,192);

	public FileChooser(Frame owner) {
		super(owner);
		setFocusable(true);
		getContentPane().setBackground(bg);
		controller = (DrawPad) owner;
		setLayout(new GridBagLayout());

		addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				setVisible(false);
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void pop() {
		getContentPane().removeAll();
		int height = 0;
		GridBagConstraints gc = GridBagLayoutUtil.getDefaultConstraints();
		gc.fill = GridBagConstraints.BOTH;
		gc.insets = new Insets(2, 2, 2, 2);
		List<String> fileList = controller.getMaterialList();
		int index = 0;
		for (String fileName : fileList) {
			gc.gridy = index;
			JLabel fileNameLabel = new ClickableLabel(fileName) {

				
				@Override
				protected void mouseExited() {
					// TODO Auto-generated method stub
					super.mouseExited();
					setForeground(Color.black);
				}

				@Override
				protected void mouseEntered() {
					super.mouseEntered();
					
					setForeground(Color.green);
					
				}

				@Override
				public void mouseClicked() {
					controller.showMaterial(getText());
				}
			};
			fileNameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
			add(fileNameLabel, gc);
			index++;
			height = height + fileNameLabel.getBounds().height;
		}
		Rectangle rect = DrawPad.getInstance().getBounds();
		setBounds((rect.x + (rect.width / 2)) - (width / 2), rect.y + 100, width, index * 32);
		setPreferredSize(new Dimension(width, index * 32));
		pack();
		setVisible(true);
		requestFocus();
	}

}
