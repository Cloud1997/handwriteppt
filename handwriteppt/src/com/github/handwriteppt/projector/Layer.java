package com.github.handwriteppt.projector;

import javax.swing.Icon;
import javax.swing.JLabel;

public class Layer{
	
	private int number;
	
	private JLabel image=new JLabel();

	public Layer(int number) {
		super();
		this.number = number;
	}
	
	public void setImage(Icon image){
		this.image.setIcon(image);
	}
	
	public JLabel getImage(){
		return image;
	}
	
	public int getNumber(){
		return number;
	}
	

}
