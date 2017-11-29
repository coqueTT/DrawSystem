package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import Drawing.MyDrawing;
import drawSystem.Mediator;
import drawSystem.MyCanvas;

public class NonFilterItem extends JMenuItem{
	Mediator mediator;
	public NonFilterItem(MyCanvas canvas) {
		super("取り消し");
		mediator = canvas.getMediator();
		addActionListener(new NonFilterListener());
	}
	
	class NonFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			mediator.nonFilter();
		}
	}
}