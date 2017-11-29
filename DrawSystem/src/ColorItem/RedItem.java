package ColorItem;

import java.awt.Color;

import javax.swing.JMenuItem;
public class RedItem extends JMenuItem{
	public RedItem(ColorChangeManager colorManager) {
		super("赤");
		ColorItemListener colorItemListener= new ColorItemListener(colorManager);
		colorItemListener.setColor(new Color(255, 70, 30));
		addActionListener(colorItemListener);
	}
}
