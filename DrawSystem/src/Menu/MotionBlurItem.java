package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import Drawing.MyDrawing;
import Filter.MotionBlur;
import drawSystem.Mediator;
import drawSystem.MyCanvas;

public class MotionBlurItem extends JMenuItem{
	Mediator mediator;
	public MotionBlurItem(MyCanvas canvas) {
		super("モーションブラー");
		mediator = canvas.getMediator();
		addActionListener(new MotionBlurListener());
	}
	
	class MotionBlurListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			mediator.setFilter(new MotionBlur());
		}
	}
}
