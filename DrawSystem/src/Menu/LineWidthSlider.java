package Menu;

import javax.swing.JSlider;
import javax.swing.event.*;
import drawSystem.Mediator;

public class LineWidthSlider extends JSlider{
	Mediator mediator;
	int width = 1;
	public LineWidthSlider(Mediator mediator) {
		super(0, 100, 1);
		setLabelTable(createStandardLabels(20));
		setPaintLabels(true);
		this.mediator = mediator;
		addChangeListener(new LineWidthListener());
	}
	
	class LineWidthListener implements ChangeListener{
		public void stateChanged(ChangeEvent e) {
			mediator.setLineWidth(getValue());
		}
	}
}
