package StateManager;

import java.awt.event.*;
import javax.swing.*;

import Drawing.MyHexagram;

public class HexagramButton extends JButton {
	StateManager stateManager;
	MyHexagram myHexagram;
	int x1, y1, x2, y2;
	int w, h;
	public HexagramButton(StateManager stateManager){
		super();
		addActionListener(new HexagramListener());
		this.stateManager = stateManager;
	}
	
	class HexagramListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			stateManager.setState(new HexagramState(stateManager));
		}
	}
	
	class HexagramState implements State {
		StateManager stateManager;
		
		public HexagramState(StateManager stateManager) {
			this.stateManager = stateManager;
		}
		
		public void mouseDown(int x, int y) {
			x1 = x;
			y1 = y;
			myHexagram = new MyHexagram(x1, y1, 0, 0);
			stateManager.addDrawing(myHexagram);
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
			myHexagram.setSize(w, h);
			stateManager.repaint();
		};
	}
}
