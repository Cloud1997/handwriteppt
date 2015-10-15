package com.github.handwriteppt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

public abstract class ColorWindow extends JWindow{
 public	ColorWindow(Frame owner){
		super(owner);
		init();
		
	}
 
 public ColorWindow(Window w){
	 super(w);
	 init();
 }
 
 	private void init(){

		setFocusable(true);
		addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				setVisible(false);
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				
			}
		});
		
		JPanel p=new JPanel();
		p.setPreferredSize(new Dimension(300, 150));
		setContentPane(p);
		p.setLayout(new GridBagLayout());
		GridBagConstraints gc=	GridBagLayoutUtil.getDefaultConstraints();
		gc.fill=GridBagConstraints.BOTH;
		gc.insets=new Insets(2, 2, 2, 2);
		
	Color[] colors=	ColorsUtil.get48Colors();
	int index=0;
		for(int i=0;i<6;i++){
			for(int j=0;j<8;j++){
				gc.gridx=j;
				gc.gridy=i;
				final JLabel label=new JLabel();
				label.setOpaque(true);
				label.setBackground(colors[index++]);
				label.setPreferredSize(new Dimension(30, 20));
				label.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
					if(e.getButton()==MouseEvent.BUTTON1){
						ColorWindow.this.setVisible(false);
						colorChoosed(label.getBackground());
					}
					}
					
				});
				label.setBorder(new LineBorder(Color.black));
				p.add(label,gc);
			}
		}
		
	
 		
 	}
	
	public void pop(){
		pack();
		setVisible(true);
		requestFocus();
	}
	
	public abstract void colorChoosed(Color c);
}