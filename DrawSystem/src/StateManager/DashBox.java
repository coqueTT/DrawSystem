package StateManager;

import java.awt.event.*;
import javax.swing.*;

public class DashBox extends JCheckBox {
	StateManager stateManager;
	public DashBox(StateManager stateManager){
		super("破線");
		addItemListener(new DashListener());
		this.stateManager = stateManager;
	}

	class DashListener implements ItemListener {
		public void itemStateChanged(ItemEvent e){
			stateManager.setDash(e.getStateChange() == e.SELECTED);
		}
	}
}