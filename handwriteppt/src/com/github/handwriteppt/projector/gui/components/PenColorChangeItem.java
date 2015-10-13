package com.github.handwriteppt.projector.gui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;

import com.github.handwriteppt.Action;
import com.github.handwriteppt.Item;

public class PenColorChangeItem extends JComboBox<Color> implements Item {
	
	private final static String RED="RED";
	private final static String BLUE="BLUE";
	private final static String BLACK="BLACK";
	private final static String GREEN="GREEN";
	
	private JLabel renderer=new JLabel();{
		renderer.setPreferredSize(new Dimension(50, 20));
		renderer.setBackground(Color.black);
		renderer.setOpaque(true);
	}

	public PenColorChangeItem() {
		super(new Color[]{Color.BLUE,Color.RED,Color.BLACK,Color.GREEN});
		setSelectedIndex(0);
		setBorder(new LineBorder(Color.black));
		for(Component com:getComponents()){
			if(com instanceof AbstractButton)
				remove(com);
			if(com instanceof JLabel){
				((JLabel) com).setBorder(null);
			}
		}
//		addItem(BLUE);
//		addItem(RED);
//		addItem(BLACK);
//		addItem(GREEN);
//		addItem(Color.BLUE);
//		addItem(Color.GREEN);
//		addItem(Color.RED);
//		addItem(Color.BLACK);
//		addItemListener(new ItemListener() {
//			
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//			Color c=	(Color) e.getItem();
//			Projector.getInstance().changePenColor(c);
//			}
//		});
		setRenderer(new ListCellRenderer<Color>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends Color> list, Color value, int index,
					boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				if(value!=null){
//					if(RED.equals(value)){
						renderer.setBackground(value);
						renderer.setText("jhaha");
						PenColorChangeItem.this.setBackground(value);
//					}
//					if(BLUE.equals(value)){
//						renderer.setBackground(Color.BLUE);
//					}
				}
//				if(value!=null){
//					renderer.setForeground(value);
//				}
				return renderer;
			}
			
		});
	}

	@Override
	public void bindAction(Action a) {
		
	}

	@Override
	public void run() {
		
	}
	
	

}
