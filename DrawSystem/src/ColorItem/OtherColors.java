package ColorItem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class OtherColors extends JMenuItem{
	ColorChangeManager colorManager;
	public OtherColors(ColorChangeManager colorManager) {
		super("他の色");
		this.colorManager = colorManager;
		addActionListener(new ChooserListener(this));
	}
	
	public class ChooserListener implements ActionListener {
		OtherColors other;

		public ChooserListener(OtherColors other) {
			this.other = other;
		}
		@Override
		public void actionPerformed(ActionEvent e){
			Color color = JColorChooser.showDialog(other, "カラーパレット", Color.WHITE);
			if(color != null){
				colorManager.colorChange(color);
			}
		}
	}
	
}
