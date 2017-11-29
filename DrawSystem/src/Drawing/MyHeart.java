package Drawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.math.*;

import drawSystem.MyDashStroke;

public class MyHeart extends MyDrawing {

	public MyHeart(int xpt, int ypt) {
		super();
		setLocation(xpt, ypt);
	}

	public MyHeart(int xpt, int ypt, int w, int h) {
		this(xpt, ypt);
		setSize(w, h);
	}

	public MyHeart(int xpt, int ypt, int w, int h, Color line, Color fill) {
		this(xpt, ypt, w, h);
		setLineColor(line);
		setFillColor(fill);
	}

	@Override
	public boolean contains(int x, int y) {
		return region.contains(x, y);
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
		
		int t = (w + h);
		int xPoints[] = new int[t];
		int yPoints[] = new int[t];
		
		for(int i = 0; i < t; i++){
			double rad = Math.PI /180 * ((double)i * 360.0 / (double)t);
			xPoints[i] = (int)(x + w / 2 - ((double)w / 2 * Math.pow(Math.sin(rad), 3.0)));
			yPoints[i] = (int)(y + h / 2.5 - ((double)h / 2.5 * Math.cos(rad) - (double)h / 5.0 * Math.cos(2 * rad) - (double)h / 14.0 * Math.cos(3 * rad) - 2 * Math.cos(4 * rad)));
		}
		

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
			g2.setColor(getShadowColor());
			for(int i = 0; i < t; i++){
				xPoints[i] += 5;
				yPoints[i] += 5;
			}
			g2.fillPolygon(xPoints, yPoints, t);
			g2.drawPolygon(xPoints, yPoints, t);
			for(int i = 0; i < t; i++){
				xPoints[i] -= 5;
				yPoints[i] -= 5;
			}
		}
		g2.setStroke(new BasicStroke(getLineWidth()));
		g2.setColor(getFillColor());
		g2.fillPolygon(xPoints, yPoints, t);
		g2.setColor(getLineColor());
		g2.drawPolygon(xPoints, yPoints, t);
		
		//同じ種類でサイズの小さい図形を描画して重線を表現
		for(int i = 0; i < getNLine() - 1; i++){
			//n重線の位置を調整			
			double ratioX;
			double ratioY;
			if((ratioX = w/h) < 1.0) ratioX = 1.0;
			if((ratioY = h/w) < 1.0) ratioY = 1.0;
			double newX = x + 2 * (i + 1) * getLineWidth() * ratioX;
			double newY = y + 2 * (i + 1) * getLineWidth() * ratioY;
			double newW= w - 4 * (i + 1)* getLineWidth() * ratioX;
			double newH= h - 4 * (i + 1)* getLineWidth() * ratioY;
			
			
			MyDrawing NHeart = new MyHeart((int)newX , (int)newY, (int)newW, (int)newH, getLineColor(), getFillColor());
			System.out.println("call: " + i + "\n");
			NHeart.setNLine(1);
			NHeart.setLineWidth(getLineWidth());
			NHeart.setDashed(getDashed());
			
			//newWまたはnewHが負の値を取る時、一番外側の図より内側の図が大きくなってしまうので描画しない
			if(Math.min(newW ,newH ) < 0 ) {
			}
			else		NHeart.draw(g2);
		}
		
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
