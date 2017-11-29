package StateManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;

public class LineColorButton extends JButton{
	StateManager stateManager;
	JColorChooser colorChooser;
	
	public LineColorButton(StateManager s, JColorChooser j){
		super("線");
		this.stateManager = s;
		this.colorChooser = j;
		addActionListener(new LineColorListener());
	}
	
	class LineColorListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			stateManager.setLineColor(colorChooser.getColor());
		}
	}
}
