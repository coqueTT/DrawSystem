package StateManager;

import java.awt.event.*;
import javax.swing.*;

public class LineWidthField extends JTextField {
	StateManager stateManager;
	public LineWidthField(StateManager stateManager){
		super("1");
		addActionListener(new LineWidthListener());
		this.stateManager = stateManager;
	}
	
	class LineWidthListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			stateManager.setLineWidth(Integer.parseInt(getText()));
		}
	}
}