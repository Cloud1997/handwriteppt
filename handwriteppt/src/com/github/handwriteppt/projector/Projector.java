package com.github.handwriteppt.projector;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.github.handwriteppt.projector.gui.components.ControlPanel;
import com.github.handwriteppt.projector.gui.components.ShowPanel;

public class Projector {
	
	private List<Page> pageList=new ArrayList<Page>();
	private JPanel mainBoard=new JPanel();
	private JFrame window=new JFrame("Test");
	{
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private ShowPanel showPanel=new ShowPanel();
	private ControlPanel controlPanel=new ControlPanel();
	private JPanel blackPanel=new JPanel();
	private Page currentPage;
	private Layer currentLayer;
	private GridBagConstraints gc=new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
	
	private static Projector instance;
	
	public static Projector getInstance(){
		return instance;
	}
	
	public static void show(String file){
		instance=new Projector();
		instance.load(file);
	SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				instance.initComponents();
				instance.prepareListeners();
				instance.start();
			}
		});
	}
	
	public void close(){
		window.dispose();
		instance=null;
	}

	public static void main(String[] args) {
		show("res/show.zip");
	}
	
	public void load(String file){
		try {
			byte[] caches=new byte[2048];
			ZipFile zf=new ZipFile(new File(file),ZipFile.OPEN_READ);
			Enumeration enu=zf.entries();
			Page currentPage=null;
			while(enu.hasMoreElements()){
				ZipEntry ze=(ZipEntry) enu.nextElement();
				String name=ze.getName();
				int indexOfLine=name.indexOf("-");
				int indexOfDot=name.indexOf(".");
				int pageNumber=Integer.parseInt(name.substring(0, indexOfLine));
				int layerNumber=Integer.parseInt(name.substring(indexOfLine+1,indexOfDot));
				if(pageNumber>pageList.size()){
					currentPage=new Page(pageNumber);
					pageList.add(currentPage);
				}
				Layer layer=new Layer(layerNumber);
				InputStream stream=	zf.getInputStream(ze);
				int length=0;
				ByteArrayOutputStream baos=new ByteArrayOutputStream(2048);
				while((length=stream.read(caches))!=-1){
					baos.write(caches, 0, length);
				}
			    baos.flush();
			    
				layer.setImage(new ImageIcon(baos.toByteArray()));
				baos.close();
				currentPage.addLayer(layer);
			}
			zf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initComponents(){
		
		window.setContentPane(mainBoard);
		mainBoard.setOpaque(true);
		mainBoard.setLayout(new GridBagLayout());
		mainBoard.add(showPanel.getPanel(),gc);
		gc.gridy=1;
		gc.weighty=0;
		gc.fill=GridBagConstraints.HORIZONTAL;
		controlPanel.getPanel().setVisible(false);
		mainBoard.add(controlPanel.getPanel(),gc);
		gc.gridy=2;
		blackPanel.setPreferredSize(new Dimension(100, 50));
		blackPanel.setBackground(Color.white);
		mainBoard.add(blackPanel, gc);
	}
	
	
	private void prepareListeners(){
		window.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_SPACE){
					layerForward();
				}else if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
					close();
				}
			}
			
		});
		controlPanel.getPanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				controlPanelVisible(false);
			}
		});
		blackPanel.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				controlPanelVisible(true);
			}
			
		});
	}
	
	public void controlPanelVisible(boolean visible){
		blackPanel.setVisible(!visible);
		controlPanel.getPanel().setVisible(visible);
	}
	
	public void start(){
//		GraphicsEnvironment  ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
//		GraphicsDevice gd=	ge.getDefaultScreenDevice();
//		gd.setFullScreenWindow(window);
		Dimension screen=	Toolkit.getDefaultToolkit().getScreenSize();
		window.setPreferredSize(new Dimension(640,480));
		currentPage=pageList.get(0);
		currentLayer=currentPage.getLayer(1);
		showPanel.show(currentPage, 1);
		window.pack();
		window.setVisible(true);
		
	}
	
	public Page getCurrentPage(){
		return currentPage;
	}
	
	public Layer getCurrentLayer(){
		return currentLayer;
	}
	
	public List<Page> getAllPages(){
		return pageList;
	}
	
	public void pageBack(){
		if(currentPage.getNumber()>1){
			currentPage=pageList.get(currentPage.getNumber()-2);
			int layerCount=currentPage.getLayerCount();
			currentLayer=currentPage.getLayer(layerCount);
			showPanel.show(currentPage, layerCount);
		}
	
	}
	
	public void pageForward(){
		if(currentPage.getNumber()<pageList.size()){
			currentPage=pageList.get(currentPage.getNumber());
			currentLayer=currentPage.getLayer(1);
			showPanel.show(currentPage, 1);
		}
	}
	
	public void layerForward(){
		if(currentPage.getLayerCount()>currentLayer.getNumber()){
			currentLayer=currentPage.getLayer(currentLayer.getNumber()+1);
			showPanel.showLayer(currentLayer.getNumber());
		}else{
			pageForward();
		}
	}
	
	public void layerBack(){
		if(currentLayer.getNumber()>1){
			currentLayer=currentPage.getLayer(currentLayer.getNumber()-1);
			showPanel.hideLayer(currentLayer.getNumber()+1);
			
		}else{
			pageBack();
		}
	}


}
