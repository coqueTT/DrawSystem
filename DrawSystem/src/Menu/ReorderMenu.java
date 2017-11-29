package Menu;

import javax.swing.JMenu;
import drawSystem.MyCanvas;

public class ReorderMenu extends JMenu{
	public ReorderMenu(MyCanvas canvas) {
		super("整列");
		
		ReorderPreItem reorderPreItem = new ReorderPreItem(canvas.getMediator());
		ReorderSubItem reorderSubItem = new ReorderSubItem(canvas.getMediator());
		ReorderLastItem reorderLastItem = new ReorderLastItem(canvas.getMediator());
		ReorderFirstItem reorderFirstItem = new ReorderFirstItem(canvas.getMediator());
		//add
		add(reorderPreItem);
		add(reorderSubItem);
		add(reorderFirstItem);
		add(reorderLastItem);
	}
}
