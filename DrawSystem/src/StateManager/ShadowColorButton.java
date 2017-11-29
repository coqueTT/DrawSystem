package StateManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;

public class ShadowColorButton extends JButton{
	StateManager stateManager;
	JColorChooser colorChooser;
	
	public ShadowColorButton(StateManager s, JColorChooser j){
		super("影");
		this.stateManager = s;
		this.colorChooser = j;
		addActionListener(new ShadowColorListener());
	}
	
	class ShadowColorListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			stateManager.setShadowColor(colorChooser.getColor());
		}
	}
}
