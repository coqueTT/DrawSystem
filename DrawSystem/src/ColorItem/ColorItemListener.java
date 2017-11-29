package ColorItem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorItemListener implements ActionListener {
	Color color;
	ColorChangeManager colorManager;

	public ColorItemListener(ColorChangeManager colorManager) {
		this.colorManager = colorManager;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		colorManager.colorChange(color);
	}
}
