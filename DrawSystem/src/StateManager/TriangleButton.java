package StateManager;

import java.awt.event.*;
import javax.swing.*;

import Drawing.MyTriangle;

public class TriangleButton extends JButton {
	StateManager stateManager;
	MyTriangle myTriangle;
	int x1, y1, x2, y2;
	int w, h;
	public TriangleButton(StateManager stateManager){
		super();
		addActionListener(new TriangleListener());
		this.stateManager = stateManager;
	}

	class TriangleListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			stateManager.setState(new TriangleState(stateManager));
		}
	}

	class TriangleState implements State {
		StateManager stateManager;

		public TriangleState(StateManager stateManager) {
			this.stateManager = stateManager;
		}

		public void mouseDown(int x, int y) {
			x1 = x;
			y1 = y;
			myTriangle = new MyTriangle(x1, y1, 0, 0);
			stateManager.addDrawing(myTriangle);
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
			myTriangle.setSize(w, h);
			stateManager.repaint();
		};
	}
}
