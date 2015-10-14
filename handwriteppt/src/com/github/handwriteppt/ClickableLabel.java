package com.github.handwriteppt;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public abstract class ClickableLabel extends JLabel
//    implements MouseListener
{
//  protected JFrame controller;
  
  private static Border upBoader=BorderFactory.createBevelBorder(EtchedBorder.RAISED);
  private static Border downBoader=BorderFactory.createBevelBorder(EtchedBorder.LOWERED); 		  

//  public ClickableLabel(JFrame controller)
//  {
//    super();
//    addMouseListener(this);
//  }

//  public ClickableLabel(Icon image, JFrame controller)
//  {
//    super(image);
//    this.controller = controller;
//    addMouseListener(this);
//  }
  public ClickableLabel(Icon image){
	  super(image);
	  setPreferredSize(new Dimension(image.getIconWidth()+5, image.getIconHeight()+5));
	  addMouseListener(new MouseAdapter() {

		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getButton()==MouseEvent.BUTTON1)
			  setBorder(downBoader);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.getButton()==MouseEvent.BUTTON1)
			setBorder(upBoader);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			setBorder(upBoader);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			setBorder(null);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton()==MouseEvent.BUTTON1)
				ClickableLabel.this.mouseClicked();
		}
		
		
		  
	});
  }

//  public ClickableLabel(String text, JFrame controller)
//  {
//    super(text);
//    this.controller = controller;
//    addMouseListener(this);
//  }

  public abstract void mouseClicked();
//
//  @Override
//  public void mousePressed(MouseEvent e)
//  {
//    // TODO Auto-generated method stub
//
//  }
//
//  @Override
//  public void mouseReleased(MouseEvent e)
//  {
//    // TODO Auto-generated method stub
//
//  }
//
//  @Override
//  public void mouseEntered(MouseEvent e)
//  {
//    // TODO Auto-generated method stub
//
//  }
//
//  @Override
//  public void mouseExited(MouseEvent e)
//  {
//    // TODO Auto-generated method stub
//
//  }

}
