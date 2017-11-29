package Menu;

import javax.swing.JMenu;
import drawSystem.MyCanvas;

public class ShadowMenu extends JMenu{
	public ShadowMenu(MyCanvas canvas) {
		super("影");
		Shadow shadow = new Shadow(canvas.getMediator());
		
		add(shadow);
	}
}
