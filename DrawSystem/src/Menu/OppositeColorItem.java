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
import Filter.OppositeColorFilter;
import drawSystem.Mediator;
import drawSystem.MyCanvas;

public class OppositeColorItem extends JMenuItem{
	Mediator mediator;
	public OppositeColorItem(MyCanvas canvas) {
		super("反対色");
		mediator = canvas.getMediator();
		addActionListener(new OppositeColorListener());
	}
	
	class OppositeColorListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			mediator.setFilter(new OppositeColorFilter());
		}
	}
}
