package StateManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;

public class FillColorButton extends JButton{
	StateManager stateManager;
	JColorChooser colorChooser;
	
	public FillColorButton(StateManager s, JColorChooser j){
		super("塗り");
		this.stateManager = s;
		this.colorChooser = j;
		addActionListener(new FillColorListener());
	}
	
	class FillColorListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			stateManager.setFillColor(colorChooser.getColor());
		}
	}
}
