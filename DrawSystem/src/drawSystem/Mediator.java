package drawSystem;

import java.awt.Color;
import java.util.Enumeration;
import java.util.Vector;
import Drawing.MyDrawing;
import Drawing.MyRectangle;
import Filter.Filter;
import Filter.Laplacian;
import Filter.Mosaic;
import Filter.MotionBlur;
import Filter.Sharpness;

public class Mediator {

	Vector<MyDrawing> drawings = new Vector<MyDrawing>();
	MyCanvas canvas;
	Vector<MyDrawing> selectedDrawings = new Vector<MyDrawing>();
	Vector<MyDrawing> buffer = new Vector<MyDrawing>(); // Cut, Copyバッファ
	
	public Mediator(MyCanvas canvas) {
		this.canvas = canvas;
		drawings = new Vector<MyDrawing>();
	}
	
	public Enumeration<MyDrawing> drawingsElements() {
		return drawings.elements();
	}
	
	public void addDrawing(MyDrawing d) {
		drawings.add(d);
		clearSelected();
	}
	
	public void removeDrawing(MyDrawing d) {
		drawings.remove(d);
		repaint();
	}
	
	public void removeSelectedDrawing() {	
		for(MyDrawing d : selectedDrawings)
			removeDrawing(d);
	}
	
	public Vector<MyDrawing> getDrawings() {
		return drawings;
	}
	
	public void setDrawings(Vector<MyDrawing> drawings) {
		this.drawings = drawings;
	}
	public Vector<MyDrawing> getSelectedDrawing() {
		return selectedDrawings;
	}
	
	public void move(int dx, int dy) {
		for(MyDrawing d : selectedDrawings)
			d.move(dx, dy);
		repaint();
	}
	
	public void resize(int x, int y, int w, int h) {
		for(MyDrawing d : selectedDrawings)
			d.resize(x, y, w, h);
		repaint();
	}
	
	public void rotate(double theta) {
		int i = 0;
		for(MyDrawing d : selectedDrawings){
			d.setTheta(theta);
			System.out.println(++i);
		}
		repaint();
	}
	
	public void repaint() {
		canvas.repaint();
	}
	
	public void setSelectedDrawing(MyDrawing d) {
		if(!selectedDrawings.contains(d))
			selectedDrawings.add(d);
		applySelected();
		repaint();
	}
	
	public boolean setSelected(int x, int y) {
		MyDrawing d;
		int index;
		boolean hit = false;
		for(index = drawings.size() - 1; index >= 0; index--) {
			d = drawings.get(index);
			if(d.contains(x, y)) {
				setSelectedDrawing(d);
				hit = true;
				break;
			}
		}
		applySelected();
		repaint();
		return hit;
	}
	
	
	public  void setSelected(MyRectangle rect) {
		for(MyDrawing d : drawings) {
			if(rect.contains(d.getX(), d.getY(), d.getW(), d.getH())) {
			//if(d.contains(rect.getX(), rect.getY(), rect.getW(), rect.getH())){
				setSelectedDrawing(d);
			}
			repaint();
		}
	}
	
	public int setSelected_resize(int x, int y) {
		MyDrawing d;
		int index;
		int number = -1;
		for(index = drawings.size() - 1; index >= 0; index--) {
			d = drawings.get(index);
			if((number = d.contains_resize(x, y)) != -1) {
				clearSelected();
				setSelectedDrawing(d);
				break;
			}
		}
		applySelected();
		repaint();
		return number;
	}
	
	public boolean setSelected_rotate(int x, int y) {
		MyDrawing d;
		int index;
		for(index = drawings.size() - 1; index >= 0; index--) {
			d = drawings.get(index);
			if(d.contains_rotate(x, y)) {
				clearSelected();
				setSelectedDrawing(d);
				applySelected();
				repaint();
				return true;
			}
		}
		return false;
	}
	
	public void clearSelected() {
		selectedDrawings.clear();
	}
	
	//selectedDrawingのMyDrawingインスタンスのIsSelectedフラグをtrueに
	//それ以外のIsSelectedをfalseにする
	public void applySelected() {
		for(MyDrawing d : drawings) {
			d.setSelected(false);
			if(selectedDrawings.contains(d))
				d.setSelected(true);
		}
	}

	public void clearBuffer()
	{
	  buffer.clear();
	}

	public void copy()
	{
	  // バッファをクリアする
	  clearBuffer();
	  for(MyDrawing d : selectedDrawings)
		  buffer.add((MyDrawing)d.clone());
	}

	public void cut()
	{
	  copy();
	  for(MyDrawing d : selectedDrawings)
		  removeDrawing(d); // drawingsからselectedDrawingを削除。
	  repaint();
	}

	//コピー元図形の位置から少しずらしてペースト
	public void paste()
	{
		for(MyDrawing drawing : buffer) {
			MyDrawing d = (MyDrawing)drawing.clone();
			d.setLocation(d.getX() + 10, d.getY() - 10);
			drawings.add(d);
		}
		applySelected();
		repaint();
	}
	
	public void setShadow() {
		for(MyDrawing d : selectedDrawings){
			d.setShadow(!d.getShadow());
		}
		repaint();
	}
	
	public void setLineWidth(int width){
		for(MyDrawing d : selectedDrawings) {
			d.setLineWidth(width);
		}
		repaint();
	}
	
	//fillColor
	public void setFillColor(Color color) {
		for(MyDrawing d : selectedDrawings) {
			d.setFillColor(color);
		}
		repaint();
	}
	
	public void setFillTransparent(int transparent) {
		for(MyDrawing d : selectedDrawings) {
			d.setFillTransparent(transparent);
		}
		repaint();
	}
	
	//lineColor
	public void setLineColor(Color color) {
		for(MyDrawing d : selectedDrawings) {
			d.setLineColor(color);
		}
		repaint();
	}
	
	public void setLineTransparent(int transparent) {
		for(MyDrawing d : selectedDrawings) {
			d.setLineTransparent(transparent);
		}
		repaint();
	}
	
	//shadowColor
	public void setShadowColor(Color color) {
		for(MyDrawing d : selectedDrawings) {
			d.setShadowColor(color);
		}
		repaint();
	}
	
	public void setShadowTransparent(int transparent) {
		for(MyDrawing d : selectedDrawings) {
			d.setShadowTransparent(transparent);
		}
		repaint();
	}
	
	
	//Filter
	public void nonFilter() {
		for(MyDrawing d : selectedDrawings) {
			d.setIsFilter(false);
		}
		repaint();
	}
	
	public void setFilter(Filter filter) {
		for(MyDrawing d : selectedDrawings) {
			d.setIsFilter(true);
			d.setFilter(filter);
		}
		repaint();
	}
	
	//順序変更
	//一つ背面に
	public void reorder_sub() {
		for(int i = 1; i < drawings.size(); i++) {
			MyDrawing d = drawings.elementAt(i);
			if(selectedDrawings.contains(d)) {
				//最背面が選択されている状態の時
				//背面処理をした後、順番が変わらない図形が存在するので整合性を取る
				if(selectedDrawings.contains(drawings.elementAt(i - 1)))
					continue;
				MyDrawing tmp = drawings.elementAt(i - 1);
				drawings.setElementAt(d, i - 1);
				drawings.setElementAt(tmp, i);
			}
		}
		repaint();
	}
	//一つ前面に
	public void reorder_pre() {
		for(int i = drawings.size() - 2; i >= 0; i--) {
			MyDrawing d = drawings.elementAt(i);
			if(selectedDrawings.contains(d)) {
				//最前面が選択されている状態の時
				//背面処理をした後、順番が変わらない図形が存在するので整合性を取る
				if(selectedDrawings.contains(drawings.elementAt(i + 1)))
					continue;
				MyDrawing tmp = drawings.elementAt(i + 1);
				drawings.setElementAt(d, i + 1);
				drawings.setElementAt(tmp, i);
			}
		}
		repaint();
	}
	//最背面に
	public void reorder_last() {
		int n = 0;
		for(int i = drawings.size() - 1; i >= n; i--) {
			MyDrawing d = drawings.elementAt(i);
			if(selectedDrawings.contains(d)) {
				drawings.remove(i);
				drawings.add(0, d);
				i++;
				n++;
			}
		}
		repaint();
	}
	
	//最前面に
	public void reorder_first() {
		int n = drawings.size();
		for(int i = 0; i < n; i++) {
			MyDrawing d = drawings.elementAt(i);
			if(selectedDrawings.contains(d)) {
				drawings.remove(i);
				drawings.add(d);
				i--;
				n--;
			}
		}
		repaint();
	}
}
