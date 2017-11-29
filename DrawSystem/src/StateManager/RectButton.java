package StateManager;

import java.awt.event.*;
import javax.swing.*;

import Drawing.MyRectangle;

public class RectButton extends JButton {
	StateManager stateManager;
	MyRectangle myRectangle;
	int x1, y1, x2, y2;
	int w, h;
	public RectButton(StateManager stateManager){
		super();
		addActionListener(new RectListener());
		this.stateManager = stateManager;
	}

	class RectListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			stateManager.setState(new RectState(stateManager));
		}
	}

	class RectState implements State {
		StateManager stateManager;

		public RectState(StateManager stateManager) {
			this.stateManager = stateManager;
		}

		public void mouseDown(int x, int y) {
			x1 = x;
			y1 = y;
			myRectangle = new MyRectangle(x1, y1, 0, 0);
			stateManager.addDrawing(myRectangle);
		}


		//後に実装
		public void mouseUp(int x, int y){}
		public void mouseDrag(int x, int y){
			x2 = x;
			y2 = y;
			//w = x2 -x1 >= 0 ? x2 - x1 : x1 - x2;
			//h = y2 - y1 >= 0 ? y2 - y1 : y1 - y2;
			w = x2 - x1;
			h = y2 - y1;
			//
			myRectangle.setSize(w, h);
			stateManager.repaint();
		}
	}
}
