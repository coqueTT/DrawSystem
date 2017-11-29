package StateManager;

import java.awt.event.*;
import javax.swing.*;

public class NLineField extends JTextField {
	StateManager stateManager;
	public NLineField(StateManager stateManager){
		super("1");
		addActionListener(new LineWidthListener());
		this.stateManager = stateManager;
	}
	
	class LineWidthListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			stateManager.setNLine(Integer.parseInt(getText()));
		}
	}
}