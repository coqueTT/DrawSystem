package ColorItem;

import java.awt.Color;

import javax.swing.JMenuItem;

public class BlueItem extends JMenuItem{

	public BlueItem(ColorChangeManager med) {
		super("青");
		ColorItemListener colorItemListener= new ColorItemListener(med);
		colorItemListener.setColor(new Color(30, 70, 255));
		addActionListener(colorItemListener);
	}
}
