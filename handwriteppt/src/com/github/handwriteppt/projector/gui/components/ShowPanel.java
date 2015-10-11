package com.github.handwriteppt.projector.gui.components;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.github.handwriteppt.projector.Layer;
import com.github.handwriteppt.projector.Page;
import com.github.handwriteppt.projector.Projector;

public class ShowPanel {
	
	private JPanel panel;
	private GridBagConstraints gc=new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
	private JLayeredPane lp;

	public ShowPanel(){
		initUI();
	}
	
	private void initUI(){
		panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setOpaque(true);
        lp=new JLayeredPane();
		lp.setLayout(null);
		panel.add(lp,gc);
		panel.addMouseWheelListener(new MouseAdapter(){
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				
				
				if(e.getWheelRotation()<0){
					Projector.getInstance().layerBack();
				}else{
					Projector.getInstance().layerForward();
				}
			}
		});
		panel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1){
					Projector.getInstance().layerForward();
				}
			}

			
			
			
			
		});
	}

	public void show(Page page,int layer_no){
	lp.removeAll();
//	List<Page> list=	Projector.getInstance().getAllPages();
//	Page page=	list.get(page_no-1);
	for(int i=1;i<=layer_no;i++){
		Layer layer=page.getLayer(i);
		JLabel image=layer.getImage();
		image.setBounds(0, 0, image.getIcon().getIconWidth(), image.getIcon().getIconHeight());
		
		lp.add(image,Integer.valueOf(layer.getNumber()),layer.getNumber());
		System.out.println("show: Add layer "+layer.getNumber());
	}
	panel.repaint();
	}
	
	public void showLayer(int layer_no){
		Page page=Projector.getInstance().getCurrentPage();
//		Layer layer=pj.getCurrentLayer();
		if(page.getLayerCount()>=layer_no){
			Layer layer=page.getLayer(layer_no);
			JLabel image=	layer.getImage();
			image.setBounds(0, 0, image.getIcon().getIconWidth(), image.getIcon().getIconHeight());
			lp.add(image, layer_no,layer_no);
			System.out.println("showLayer: Add layer "+layer_no);
		}
		panel.repaint();
	}
	
	public void hideLayer(int layer_no){
		System.out.println("Remove layer "+layer_no);
//		Page page=Projector.getInstance().getCurrentPage();
//		Layer layer=page.getLayer(layer_no);
		Component[] comps=lp.getComponentsInLayer(layer_no);
		
		lp.remove(comps[0]);
		panel.repaint();
	}
	
	public JPanel getPanel(){
		return panel;
	}
}
