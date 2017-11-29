package drawSystem;
import java.util.*;
import java.awt.*;
import javax.swing.*;

import Drawing.MyDrawing;

public class MyCanvas extends JPanel 
{

	Mediator mediator;

	public MyCanvas() {
		setBackground(Color.white);
		this.mediator =  new Mediator(this);
	}

	public void paint(Graphics g) {
		super.paint(g);
		
		Enumeration<MyDrawing> e = mediator.drawingsElements();
		while(e.hasMoreElements()) {
			MyDrawing d = e.nextElement();
			d.draw(g);
		}
	}
	
	public Mediator getMediator() {
		return mediator;
	}
}
