package Menu;

import javax.swing.JSlider;
import javax.swing.event.*;

import ColorItem.ColorChangeManager;
import ColorItem.ColorState;
import drawSystem.Mediator;

public class TransparentSlider extends JSlider{
	ColorChangeManager colorManager;
	int transparent;
	public TransparentSlider(ColorChangeManager manager) {
		super(0, 255, 0);
		setLabelTable(createStandardLabels(51));
		setPaintLabels(true);
		this.colorManager = manager;
		addChangeListener(new TransparentListener());
	}
	
	class TransparentListener implements ChangeListener{
		public void stateChanged(ChangeEvent e) {
			colorManager.transparentChange(getValue());
		}
	}
}
