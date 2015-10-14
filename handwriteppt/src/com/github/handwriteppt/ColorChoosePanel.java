package com.github.handwriteppt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

public class ColorChoosePanel extends JPanel{
	private JLabel colorChooser;
	private JLabel currentColor=new JLabel();
	private JWindow win;
	private Color[] colors=new Color[]{Color.BLACK,Color.BLUE};
	public ColorChoosePanel(Icon icon){
		win=new ColorWindow(DrawPad.getInstance());
		colorChooser=new ClickableLabel(icon){

			@Override
			public void mouseClicked() {
				popColorChooserWindow();
			}
			
		};
		add(colorChooser);
		currentColor.setOpaque(true);
		currentColor.setBackground(Color.black);
		add(currentColor);
		
	}
	
	private void popColorChooserWindow(){
		win.setVisible(true);
		
	}
	
	private class ColorWindow extends JWindow{
		ColorWindow(Frame owner){
			super(owner);
			setPreferredSize(new Dimension(400, 400));
			JPanel p=new JPanel();
			p.setPreferredSize(new Dimension(400, 400));
			setContentPane(p);
			p.setLayout(new GridBagLayout());
			GridBagConstraints gc=	GridBagLayoutUtil.getDefaultConstraints();
			gc.fill=GridBagConstraints.BOTH;
			final JLabel label=new JLabel();
			label.setOpaque(true);
			label.setBackground(Color.red);
			label.setPreferredSize(new Dimension(40, 40));
			label.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1){
					win.setVisible(false);
					currentColor.setBackground(label.getBackground());
					DrawPad.getInstance().changePenColor(label.getBackground());
				}
					// TODO Auto-generated method stub
				}
				
			});
			label.setBorder(new LineBorder(Color.black));
			for(int i=0;i<5;i++){
				for(int j=0;j<5;j++){
					gc.gridx=j;
					gc.gridy=i;
					
					p.add(label,gc);
				}
			}
			
		}
	}
//
//	public ColorChooseLabel(Icon image) {
//		super(image);
//	}
//
//	@Override
//	public void mouseClicked() {
//		// TODO Auto-generated method stub
//		JColorChooser cc=new JColorChooser();
//	}
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public static void main(String[] a){
//		ColorChooseLabel s=new ColorChooseLabel();
//		s.start();
//	}
//	public void start(){
//		JFrame f=new JFrame("h");
//		JButton b=new JButton("Butont");
//		b.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				JColorChooser cc=new JColorChooser();
//			}
//		});
//		f.getContentPane().add(b);
//		f.getContentPane().add(new JColorChooser());
//		f.pack();
//		f.setVisible(true);
//	
////		cc.setVisible(true);
//	}

}
