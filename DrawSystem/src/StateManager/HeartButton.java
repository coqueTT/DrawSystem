package StateManager;

import java.awt.event.*;
import javax.swing.*;

import Drawing.MyHeart;

public class HeartButton extends JButton {
	StateManager stateManager;
	MyHeart myHeart;
	int x1, y1, x2, y2;
	int w, h;
	public HeartButton(StateManager stateManager){
		super();
		addActionListener(new HeartListener());
		this.stateManager = stateManager;
	}
	
	class HeartListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			stateManager.setState(new HeartState(stateManager));
		}
	}
	
	class HeartState implements State {
		StateManager stateManager;
		
		public HeartState(StateManager stateManager) {
			this.stateManager = stateManager;
		}
		
		public void mouseDown(int x, int y) {
			x1 = x;
			y1 = y;
			myHeart = new MyHeart(x1, y1, 0, 0);
			stateManager.addDrawing(myHeart);
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
			myHeart.setSize(w, h);
			stateManager.repaint();
		};
	}
}
