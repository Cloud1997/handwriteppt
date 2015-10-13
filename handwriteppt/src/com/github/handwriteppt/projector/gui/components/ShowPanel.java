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
	private GridBagConstraints gc = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
			GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
	private JLayeredPane lp;
	private FreeDrawBoard drawBoard = new FreeDrawBoard();
	int width, height;

	public ShowPanel() {
		initUI();
	}

	private void initUI() {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setOpaque(true);
		lp = new JLayeredPane();
		lp.setLayout(null);
		panel.add(lp, gc);
		panel.addMouseWheelListener(new MouseAdapter() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				if (e.getWheelRotation() < 0) {
					Projector.getInstance().layerBack();
				} else {
					Projector.getInstance().layerForward();
				}
			}
		});
		panel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (drawBoard.getStatus() == FreeDrawBoard.DRAWED) {
						drawBoard.clearBoard();
					} else {
						Projector.getInstance().layerForward();
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (drawBoard.getStatus() == FreeDrawBoard.NOT_READY && e.getButton() == MouseEvent.BUTTON3) {
					lp.add(drawBoard, Integer.valueOf(-1));
					lp.moveToFront(drawBoard);
					drawBoard.setBounds(0, 0, width, height);
					drawBoard.initDrawer();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					drawBoard.resetXY();
				}

			}

		});

		panel.addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if (e.getModifiersEx() == MouseEvent.BUTTON3_DOWN_MASK) {
					drawBoard.drawToPoint(e.getX(), e.getY());
				}
			}

		});
	}

	public void show(Page page, int layer_no) {
		lp.removeAll();
		for (int i = 1; i <= layer_no; i++) {
			Layer layer = page.getLayer(i);
			JLabel image = layer.getImage();
			width = image.getIcon().getIconWidth();
			height = image.getIcon().getIconHeight();
			image.setBounds(0, 0, width, height);
			lp.add(image, Integer.valueOf(layer.getNumber()), layer.getNumber());
		}
		panel.repaint();
	}

	public void showLayer(int layer_no) {
		Page page = Projector.getInstance().getCurrentPage();
		if (page.getLayerCount() >= layer_no) {
			Layer layer = page.getLayer(layer_no);
			JLabel image = layer.getImage();
			image.setBounds(0, 0, image.getIcon().getIconWidth(), image.getIcon().getIconHeight());
			lp.add(image, layer_no, layer_no);
		}
		panel.repaint();
	}

	public void hideLayer(int layer_no) {
		Component[] comps = lp.getComponentsInLayer(layer_no);
		lp.remove(comps[0]);
		panel.repaint();
	}

	public JPanel getPanel() {
		return panel;
	}
}
