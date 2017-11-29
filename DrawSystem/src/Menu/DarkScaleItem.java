package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import Drawing.MyDrawing;
import Filter.DarkScaleFilter;
import Filter.Mosaic;
import drawSystem.Mediator;
import drawSystem.MyCanvas;

public class DarkScaleItem extends JMenuItem{
	Mediator mediator;
	public DarkScaleItem(MyCanvas canvas) {
		super("ダークスケール");
		mediator = canvas.getMediator();
		addActionListener(new DarkScaleListener());
	}
	
	class DarkScaleListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			mediator.setFilter(new DarkScaleFilter());
		}
	}
}