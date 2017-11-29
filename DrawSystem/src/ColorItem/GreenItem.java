package ColorItem;

import java.awt.Color;

import javax.swing.JMenuItem;

public class GreenItem extends JMenuItem{
	public GreenItem(ColorChangeManager colorManager) {
		super("緑");
		ColorItemListener colorItemListener= new ColorItemListener(colorManager);
		colorItemListener.setColor(new Color(70, 255, 30));
		addActionListener(colorItemListener);
	}
}
