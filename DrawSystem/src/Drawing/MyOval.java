package Drawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import drawSystem.MyDashStroke;

public class MyOval extends MyDrawing{

	public MyOval(int xpt, int ypt) {
		super();
		setLocation(xpt, ypt);
	}

	public MyOval(int xpt, int ypt, int w, int h) {
		this(xpt, ypt);
		setSize(w, h);
	}
	
	public MyOval(int xpt, int ypt, int w, int h, Color line, Color fill) {
		this(xpt, ypt, w, h);
		setLineColor(line);
		setFillColor(fill);
	}
	
	@Override
	public boolean contains(int x, int y) {
		return region.contains(x,  y);
	}
	
	@Override
	public void setRegion() {
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();

		if(w < 0) {
			x += w;
			w *= -1;
		}
		if(h < 0) {
			y += h;
			h *= -1;
		}
		region = new Rectangle(x, y, w, h);
	}
	
	
	@Override
	public void draw(Graphics g) {
		
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();

		if(w < 0) {
			x += w;
			w *= -1;
		}
		if(h < 0) {
			y += h;
			h *= -1;
		}
		
		int x2 = x, y2 = y;
		int line;
		x = y = line = getLineWidth() + 5;

		img = new BufferedImage((int)w + 2 * line, (int)h + 2 * line, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)img.getGraphics();
		Graphics2D g3 = (Graphics2D) g;
		//破線か否かでストロークを変更
		if(getDashed()){
			g2.setStroke(new MyDashStroke(getLineWidth()));
		}
		else {
			g2.setStroke(new BasicStroke(getLineWidth()));
		}
		
		//影の描画
		if(getShadow()){
			for(int i = 0; i < getNLine(); i++) {
				
				//n重線の位置を調整
				int lineL = 2 * i * getLineWidth();
				int lineR = 4 * i * getLineWidth();
				
				g2.setColor(getShadowColor());
				g2.fillOval(x + 5 + lineL, y + 5 + lineL, w - lineR , h - lineR);
				g2.drawOval(x + 5 + lineL, y + 5 + lineL, w - lineR, h - lineR);
			}
		}
		
		
		for(int i = 0; i < getNLine(); i++){
			
			//n重線の位置を調整
			int lineL = 2 * i * getLineWidth();
			int lineR = 4 * i * getLineWidth();
			
			g2.setColor(getFillColor());
			g2.fillOval(x + lineL, y + lineL, w - lineR, h - lineR);
			g2.setColor(getLineColor());
			g2.drawOval(x + lineL, y + lineL, w - lineR, h - lineR);
		}
		
		//線の太さ/2がwidth, heightの矩形外に出てしまうため調整
		g3.rotate(getTheta(), x2-line + w / 2, y2-line + h / 2);
		if(isFiltered) {
			img2 = filter.filter(img);
			g3.drawImage(img2, null, x2- line, y2 - line);
			super.draw(g3);
		}
		else 	{
			g3.drawImage(img, null, x2 - line, y2 - line);
			super.draw(g3);
		}
		g3.rotate(-getTheta(), x2-line + w / 2, y2-line + h / 2);
		
	}
}
