package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import Drawing.MyDrawing;
import Filter.Mosaic;
import drawSystem.Mediator;
import drawSystem.MyCanvas;

public class MosaicItem extends JMenuItem{
	Mediator mediator;
	public MosaicItem(MyCanvas canvas) {
		super("モザイク");
		mediator = canvas.getMediator();
		addActionListener(new MosaicListener());
	}
	
	class MosaicListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			mediator.setFilter(new Mosaic());
		}
	}
}
