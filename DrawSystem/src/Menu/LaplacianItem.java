package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import Drawing.MyDrawing;
import Filter.Laplacian;
import drawSystem.Mediator;
import drawSystem.MyCanvas;

public class LaplacianItem extends JMenuItem{
	Mediator mediator;
	public LaplacianItem(MyCanvas canvas) {
		super("エッジ抽出");
		mediator = canvas.getMediator();
		addActionListener(new LaplacianListener());
	}
	
	class LaplacianListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			mediator.setFilter(new Laplacian());
		}
	}
}
