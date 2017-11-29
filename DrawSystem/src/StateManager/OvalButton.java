package StateManager;

import java.awt.event.*;
import javax.swing.*;

import Drawing.MyOval;

public class OvalButton extends JButton {
	StateManager stateManager;
	MyOval myOval;
	int x1, y1, x2, y2;
	int w, h;
	public OvalButton(StateManager stateManager){
		super();
		addActionListener(new OvalListener());
		this.stateManager = stateManager;
	}
	
	class OvalListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			stateManager.setState(new OvalState(stateManager));
		}
	}
	
	class OvalState implements State {
		StateManager stateManager;
		
		public OvalState(StateManager stateManager) {
			this.stateManager = stateManager;
		}
		
		public void mouseDown(int x, int y) {
			x1 = x;
			y1 = y;
			myOval = new MyOval(x1, y1, 0, 0);
			stateManager.addDrawing(myOval);
		}
		
		
		//後に実装
		public void mouseUp(int x, int y){};
		public void mouseDrag(int x, int y){
			x2 = x;
			y2 = y;
			//w = x2 -x1 >= 0 ? x2 - x1 : x1 - x2;
			//h = y2 - y1 >= 0 ? y2 - y1 : y1 - y2;
			w = x2 - x1;
			h = y2 - y1;
			//
			myOval.setSize(w, h);
			stateManager.repaint();
		};
	}
}
