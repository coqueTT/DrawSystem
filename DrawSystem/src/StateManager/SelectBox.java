package StateManager;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

import Drawing.MyDrawing;
import Drawing.MyRectangle;
import drawSystem.*;

public class SelectBox extends JRadioButton {
	class Resize {
		public int x, y, w, h, gx, gy, gw, gh; 
		public Resize(int x, int y, int w, int h) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		}
	}
	
	StateManager stateManager;
	Mediator mediator;
	MyRectangle myRectangle;
	boolean rotate;
	boolean hit;
	int resizeMode;
	int x1, y1, x2, y2;
	//リサイズ用
	Resize resize;
	MyDrawing selectedDrawing;
	
	public SelectBox(StateManager stateManager){
		super("選択モード");
		addItemListener(new SelectListener());
		this.stateManager = stateManager;
	}
	
	class SelectListener implements ItemListener {
		public void itemStateChanged(ItemEvent e){
			stateManager.setState(new SelectState(stateManager));
			stateManager.setSelectMode(true);
		}
	}
	
	class SelectState implements State {
		StateManager stateManager;
		
		public SelectState(StateManager stateManager) {
			this.stateManager = stateManager;
			mediator = stateManager.mediator;
		}
		
		public void mouseDown(int x, int y) {
			//debug
			System.out.println("クリック:(" + x + " ," + y + ")");
			if(rotate = mediator.setSelected_rotate(x, y)) {
				System.out.println("rotate");
			}
			else if((resizeMode = mediator.setSelected_resize(x, y)) != -1){
				System.out.println(resizeMode);
				selectedDrawing = mediator.getSelectedDrawing().firstElement();
				resize = new Resize(selectedDrawing.getX(), selectedDrawing.getY(),
												selectedDrawing.getW(), selectedDrawing.getH());
			}else if(!(hit = mediator.setSelected(x, y))) {
				myRectangle = new MyRectangle(x, y, 0, 0);
				myRectangle.setFillColor(new Color(255, 255, 255, 0));
				myRectangle.setSelected(true);
				stateManager.addNoDecoratingDrawing(myRectangle);
			}
			x1 = x;
			y1 = y;
		}
		
		//後に実装
		public void mouseUp(int x, int y)
		{
			if(rotate) {
			}
			else if(resizeMode !=-1) {
			}else if(hit){
			}else {
				mediator.removeDrawing(myRectangle);
				mediator.setSelected(myRectangle);
			}
			resizeMode = -1;
		}
		
		public void mouseDrag(int x, int y){
			x2 = x;
			y2 = y;
			int dx = x2 - x1;
			int dy = y2 - y1;
			
			if(rotate){
				MyDrawing d = mediator.getSelectedDrawing().firstElement();
				double gy = y - (d.getY() + d.getH() / 2);
				double gx = x - (d.getX() + d.getW() / 2);
				double theta = Math.atan2(gy, gx);
				int cx = d.getX() - d.getLineWidth() - 7 + d.getW() / 2;
				int cy = d.getY() - d.getLineWidth() - 7 + d.getH() / 2;
				double iniTheta = Math.atan2((d.getY() + d.getH() - cy), (d.getX() + d.getW() - cx));
				mediator.rotate(theta + iniTheta);
			}
			else if(resizeMode != -1) {
				resize(x, y);
			}
			else if(hit) {
				mediator.move(dx, dy);
				x1 = x2;
				y1 = y2;
			}
			else{
				myRectangle.setSize(dx, dy);
				stateManager.repaint();
			}
		}
		
		public void resize(int dx, int dy) {
			switch(resizeMode){
			case 0: resize.gx = dx; resize.gy = dy; resize.gw = resize.x+resize.w-dx; resize.gh = resize.y+resize.h-dy; break;
			case 1: resize.gx = resize.x; resize.gy = dy; resize.gw = resize.w; resize.gh = resize.y+resize.h-dy; break;
			case 2: resize.gx = resize.x; resize.gy = dy; resize.gw = dx-resize.x; resize.gh = resize.y+resize.h-dy; break;
			case 3: resize.gx = resize.x; resize.gy = resize.y; resize.gw = dx-resize.x; resize.gh = resize.h; break;
			case 4: resize.gx = resize.x; resize.gy = resize.y; resize.gw = dx-resize.x; resize.gh = dy-resize.y; break;
			case 5: resize.gx = resize.x; resize.gy = resize.y; resize.gw = resize.w; resize.gh = dy-resize.y; break;
			case 6: resize.gx = dx; resize.gy = resize.y; resize.gw = resize.x+resize.w-dx; resize.gh = dy-resize.y; break;
			case 7: resize.gx = dx; resize.gy = resize.y; resize.gw = resize.x+resize.w-dx; resize.gh = resize.h; break;
			}
			if(resize.gw < 0){
				resize.gx += resize.gw;
				resize.gw *= -1;
			}
			if(resize.gh < 0){
				resize.gy += resize.gh;
				resize.gh *= -1;
				//resize.gh = resize.y - dy;
			}
			//System.out.println("("+ gx + ", " + gy + ", " + gw + ", " +  gh + ")");
			selectedDrawing.resize(resize.gx, resize.gy, resize.gw, resize.gh);
			mediator.repaint();
		}
		
	}
}