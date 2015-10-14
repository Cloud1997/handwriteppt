package com.github.handwriteppt.projector.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.github.handwriteppt.Action;
import com.github.handwriteppt.Item;
import com.github.handwriteppt.projector.Projector;

public class PenColorChangeItem extends JPanel implements Item {
	
	private Color[] cList=new Color[]{Color.BLUE,Color.RED,Color.BLACK,Color.GREEN,Color.ORANGE};
	private static int count=0;
	
	private JLabel renderer=new JLabel();
	
	public PenColorChangeItem(){
		setPreferredSize(new Dimension(60, 32));
		setBorder(new LineBorder(Color.BLACK));
		renderer.setPreferredSize(new Dimension(50, 20));
		renderer.setOpaque(true);
		renderer.setBackground(cList[count]);
		renderer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1){
				count++;
				count=count%cList.length;
				renderer.setBackground(cList[count]);
				Projector.getInstance().changePenColor(cList[count]);
				}
			}
		});
		add(renderer);
	}

	@Override
	public void bindAction(Action a) {
		
	}

	@Override
	public void run() {
		
	}
	
	

}
