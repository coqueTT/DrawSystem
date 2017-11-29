package Menu;

import javax.swing.JMenu;

import drawSystem.MyCanvas;

public class LineMenu extends JMenu{
	public LineMenu(MyCanvas canvas) {
		super("線");
	
		LineWidthSlider lineWidthSlider = new LineWidthSlider(canvas.getMediator());
		add(lineWidthSlider);
	}
}
