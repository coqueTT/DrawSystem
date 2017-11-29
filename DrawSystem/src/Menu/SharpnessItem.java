package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import Drawing.MyDrawing;
import Filter.Sharpness;
import drawSystem.Mediator;
import drawSystem.MyCanvas;

public class SharpnessItem extends JMenuItem{
	Mediator mediator;
	public SharpnessItem(MyCanvas canvas) {
		super("鮮鋭化");
		mediator = canvas.getMediator();
		addActionListener(new SharpnessListener());
	}
	
	class SharpnessListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			mediator.setFilter(new Sharpness());
		}
	}
}
