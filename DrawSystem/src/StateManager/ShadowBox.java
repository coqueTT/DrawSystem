package StateManager;

import java.awt.event.*;
import javax.swing.*;

public class ShadowBox extends JCheckBox {
	StateManager stateManager;
	public ShadowBox(StateManager stateManager){
		super("影");
		addItemListener(new ShadowListener());
		this.stateManager = stateManager;
	}
	
	class ShadowListener implements ItemListener {
		public void itemStateChanged(ItemEvent e){
			stateManager.setShadow(e.getStateChange() == e.SELECTED);
		}
	}
}