package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import Drawing.MyDrawing;
import Filter.DarkScaleFilter;
import Filter.GrayScaleFilter;
import Filter.Mosaic;
import drawSystem.Mediator;
import drawSystem.MyCanvas;

public class GrayScaleItem extends JMenuItem{
	Mediator mediator;
	public GrayScaleItem(MyCanvas canvas) {
		super("グレースケール");
		mediator = canvas.getMediator();
		addActionListener(new GrayScaleListener());
	}
	
	class GrayScaleListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			mediator.setFilter(new GrayScaleFilter());
		}
	}
}
