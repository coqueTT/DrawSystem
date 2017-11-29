package Drawing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import Filter.Filter;

public class MyDrawing implements Cloneable, Serializable {
	private int x, y, w, h;
	private transient Color lineColor;
	private transient Color fillColor;
	private transient Color shadowColor;
	private int lineWidth;
	private boolean isDashed;
	private boolean isShadow;
	private int nLine = 1;
	private double theta;
	
	boolean isSelected;
	boolean isFiltered;
	transient Shape region;
	transient Shape[] resizeRegion;
	transient Shape rotateRegion;
	//int[] resizeNumber;
	final int SIZE = 10; // 選択表示矩形に付く■の大きさ
	transient BufferedImage img, img2;
	transient Filter filter;

	public MyDrawing() {
		x = y = 0;
		w = h = 40;
		lineColor = Color.black;
		fillColor = Color.white;
		shadowColor = Color.black;
		lineWidth = 1;
	}

	public void draw(Graphics g) {
		g.setColor(Color.black);
		Graphics2D g2 = (Graphics2D) g;
		if (isSelected) {

			g2.setStroke(new BasicStroke(1));
			int x2 = x;
			int y2 = y;
			int w2 = w;
			int h2 = h;

			if (w2 < 0) {
				x2 += w2;
				w2 *= -1;
			}
			if (h2 < 0) {
				y2 += h2;
				h2 *= -1;
			}
			g.drawRect(x2, y2, w2, h2);

			g.fillRect(x + w / 2 - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
			g.fillRect(x - SIZE / 2, y + h / 2 - SIZE / 2, SIZE, SIZE);
			g.fillRect(x + w / 2 - SIZE / 2, y + h - SIZE / 2, SIZE, SIZE);
			g.fillRect(x + w - SIZE / 2, y + h / 2 - SIZE / 2, SIZE, SIZE);
			g.fillRect(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
			g.fillRect(x + w - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
			g.fillRect(x - SIZE / 2, y + h - SIZE / 2, SIZE, SIZE);
			g.fillRect(x + w - SIZE / 2, y + h - SIZE / 2, SIZE, SIZE);
			
			g.fillArc(x + w + 2 / 3 * SIZE, y  - 2 * SIZE, 20, 20, -90, 270);
		}
		setAllRegion();
	}

	// 包合判定用のメソッド
	public boolean contains(int x, int y) {
		// 各図形で定義
		// x, yが各図形内であればtrueを返す
		return false;
	}

	public boolean contains(int x, int y, int w, int h) {
		return region.contains(x, y, w, h);
	}

	public void setAllRegion() {
		setRegion();
		setResizeRegion();
		setRotateRegion();
	}
	public void setRegion() {
		// 各図形で定義
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();

		if (w < 0) {
			x += w;
			w *= -1;
		}
		if (h < 0) {
			y += h;
			h *= -1;
		}
		int cx = x - getLineWidth() - 7 + w / 2 ;
		int cy = y - getLineWidth() - 7 + h / 2;
		double cos = Math.cos(getTheta());
		double sin = Math.sin(getTheta());
		int[] xPoints = {0, 0, w, w};
		int[] yPoints = {0, h, h, 0};
		for(int i = 0; i < xPoints.length; i++) {
			xPoints[i] += x;
			yPoints[i] += y;
			
			int tmpX = xPoints[i];
			int tmpY = yPoints[i];
			xPoints[i] = (int)(tmpX * cos - tmpY * sin + cx - cx * cos + cy * sin);
			yPoints[i] = (int)(tmpX * sin + tmpY * cos + cy - cx * sin - cy * cos);
		}
		
		region = new Polygon(xPoints, yPoints, xPoints.length);
	}

	public void setResizeRegion() {
		resizeRegion = new Shape[8];
		int cx = x - getLineWidth() - 7 + w / 2 ;
		int cy = y - getLineWidth() - 7 + h / 2;
		double cos = Math.cos(getTheta());
		double sin = Math.sin(getTheta());
		int[] xPoints = {-SIZE / 2, -SIZE / 2, SIZE / 2, SIZE / 2};
		int[] yPoints = {-SIZE / 2, SIZE / 2, SIZE / 2, -SIZE / 2};
		for(int i = 0; i < xPoints.length; i++) {
			xPoints[i] += x;
			yPoints[i] += y;
			
			int tmpX = xPoints[i];
			int tmpY = yPoints[i];
			
			//回転
			xPoints[i] = (int)(tmpX * cos - tmpY * sin + cx - cx * cos + cy * sin);
			yPoints[i] = (int)(tmpX * sin + tmpY * cos + cy - cx * sin - cy * cos);
		}
		resizeRegion[0] = new Polygon(xPoints, yPoints, xPoints.length);

		xPoints = new int[]{-SIZE / 2, -SIZE / 2, SIZE / 2, SIZE / 2};
		yPoints = new int[]{-SIZE / 2, SIZE / 2, SIZE / 2, -SIZE / 2};
		for(int i = 0; i < xPoints.length; i++) {
			xPoints[i] += x + w / 2;
			yPoints[i] += y;
			
			int tmpX = xPoints[i];
			int tmpY = yPoints[i];
			
			//回転
			xPoints[i] = (int)(tmpX * cos - tmpY * sin + cx - cx * cos + cy * sin);
			yPoints[i] = (int)(tmpX * sin + tmpY * cos + cy - cx * sin - cy * cos);
		}
		resizeRegion[1] = new Polygon(xPoints, yPoints, xPoints.length);

		xPoints = new int[]{-SIZE / 2, -SIZE / 2, SIZE / 2, SIZE / 2};
		yPoints = new int[]{-SIZE / 2, SIZE / 2, SIZE / 2, -SIZE / 2};
		for(int i = 0; i < xPoints.length; i++) {
			xPoints[i] += x + w;
			yPoints[i] += y;
			
			int tmpX = xPoints[i];
			int tmpY = yPoints[i];
			
			//回転
			xPoints[i] = (int)(tmpX * cos - tmpY * sin + cx - cx * cos + cy * sin);
			yPoints[i] = (int)(tmpX * sin + tmpY * cos + cy - cx * sin - cy * cos);
		}
		resizeRegion[2] = new Polygon(xPoints, yPoints, xPoints.length);
		
		xPoints = new int[]{-SIZE / 2, -SIZE / 2, SIZE / 2, SIZE / 2};
		yPoints = new int[]{-SIZE / 2, SIZE / 2, SIZE / 2, -SIZE / 2};
		for(int i = 0; i < xPoints.length; i++) {
			xPoints[i] += x + w;
			yPoints[i] += y + h / 2;
			
			int tmpX = xPoints[i];
			int tmpY = yPoints[i];
			
			//回転
			xPoints[i] = (int)(tmpX * cos - tmpY * sin + cx - cx * cos + cy * sin);
			yPoints[i] = (int)(tmpX * sin + tmpY * cos + cy - cx * sin - cy * cos);
		}
		resizeRegion[3] = new Polygon(xPoints, yPoints, xPoints.length);
		
		xPoints = new int[]{-SIZE / 2, -SIZE / 2, SIZE / 2, SIZE / 2};
		yPoints = new int[]{-SIZE / 2, SIZE / 2, SIZE / 2, -SIZE / 2};
		for(int i = 0; i < xPoints.length; i++) {
			xPoints[i] += x + w;
			yPoints[i] += y + h;
			
			int tmpX = xPoints[i];
			int tmpY = yPoints[i];
			
			//回転
			xPoints[i] = (int)(tmpX * cos - tmpY * sin + cx - cx * cos + cy * sin);
			yPoints[i] = (int)(tmpX * sin + tmpY * cos + cy - cx * sin - cy * cos);
		}
		resizeRegion[4] = new Polygon(xPoints, yPoints, xPoints.length);
		
		xPoints = new int[]{-SIZE / 2, -SIZE / 2, SIZE / 2, SIZE / 2};
		yPoints = new int[]{-SIZE / 2, SIZE / 2, SIZE / 2, -SIZE / 2};
		for(int i = 0; i < xPoints.length; i++) {
			xPoints[i] += x + w / 2;
			yPoints[i] += y + h;
			
			int tmpX = xPoints[i];
			int tmpY = yPoints[i];
			
			//回転
			xPoints[i] = (int)(tmpX * cos - tmpY * sin + cx - cx * cos + cy * sin);
			yPoints[i] = (int)(tmpX * sin + tmpY * cos + cy - cx * sin - cy * cos);
		}
		resizeRegion[5] = new Polygon(xPoints, yPoints, xPoints.length);
		
		xPoints = new int[]{-SIZE / 2, -SIZE / 2, SIZE / 2, SIZE / 2};
		yPoints = new int[]{-SIZE / 2, SIZE / 2, SIZE / 2, -SIZE / 2};
		for(int i = 0; i < xPoints.length; i++) {
			xPoints[i] += x;
			yPoints[i] += y + h;
			
			int tmpX = xPoints[i];
			int tmpY = yPoints[i];
			
			//回転
			xPoints[i] = (int)(tmpX * cos - tmpY * sin + cx - cx * cos + cy * sin);
			yPoints[i] = (int)(tmpX * sin + tmpY * cos + cy - cx * sin - cy * cos);
		}
		resizeRegion[6] = new Polygon(xPoints, yPoints, xPoints.length);
		
		xPoints = new int[]{-SIZE / 2, -SIZE / 2, SIZE / 2, SIZE / 2};
		yPoints = new int[]{-SIZE / 2, SIZE / 2, SIZE / 2, -SIZE / 2};
		for(int i = 0; i < xPoints.length; i++) {
			xPoints[i] += x;
			yPoints[i] += y + h / 2;
			
			int tmpX = xPoints[i];
			int tmpY = yPoints[i];
			
			//回転
			xPoints[i] = (int)(tmpX * cos - tmpY * sin + cx - cx * cos + cy * sin);
			yPoints[i] = (int)(tmpX * sin + tmpY * cos + cy - cx * sin - cy * cos);
		}
		resizeRegion[7] = new Polygon(xPoints, yPoints, xPoints.length);
		
//		
//		resizeNumber[0] = 11;
//		resizeNumber[1] = 21;
//		resizeNumber[2] = 31;
//		resizeNumber[3] = 32;
//		resizeNumber[4] = 33;
//		resizeNumber[5] = 23;
//		resizeNumber[6] = 13;
//		resizeNumber[7] = 12;
	}

	public void setRotateRegion() {
		int cx = x - getLineWidth() - 7 + w / 2 ;
		int cy = y - getLineWidth() - 7 + h / 2;
		double cos = Math.cos(getTheta());
		double sin = Math.sin(getTheta());
		int[] xPoints = {x + w + 2 / 3 * SIZE, x + w + 2 / 3 * SIZE, x + w + 2 / 3 * SIZE + 20, x + w + 2 / 3 * SIZE + 20};
		int[] yPoints = {y  - 2 * SIZE, y  - 2 * SIZE + 20, y  - 2 * SIZE + 20, y  - 2 * SIZE};
		for(int i = 0; i < xPoints.length; i++) {
			int tmpX = xPoints[i];
			int tmpY = yPoints[i];
			
			//回転
			xPoints[i] = (int)(tmpX * cos - tmpY * sin + cx - cx * cos + cy * sin);
			yPoints[i] = (int)(tmpX * sin + tmpY * cos + cy - cx * sin - cy * cos);
		}
		rotateRegion = new Polygon(xPoints, yPoints, xPoints.length);
		//rotateRegion = new Rectangle(x + w + 2 / 3 * SIZE, y  - 2 * SIZE, 20, 20);
	}
	
	public int contains_resize(int x, int y) {
		for (int i = 0; i < resizeRegion.length; i++) {
			if (resizeRegion[i].contains(x, y))
				return i;//resizeNumber[i];
		}
		return -1;
	}

	public boolean contains_rotate(int x, int y) {
		return rotateRegion.contains(x, y);
	}
	public void move(int dx, int dy) {
		x += dx;
		y += dy;
		setAllRegion();
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
		setAllRegion();
	}

	public void setSize(int w, int h) {
		this.w = w;
		this.h = h;
		setAllRegion();
	}

	public void resize(int x, int y, int w, int h) {
		setLocation(x, y);
		setSize(w, h);
		setAllRegion();
	}
	// x,y座標、図形範囲の大きさ
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	// 線色
	public void setLineColor(Color color) {
		lineColor = color;
	}

	public Color getLineColor() {
		return lineColor;
	}
	
	public void setLineTransparent(int transparent) {
		lineColor = new Color(lineColor.getRed(), lineColor.getGreen(), lineColor.getBlue(), transparent);
	}
	
	// 内色
	public void setFillColor(Color color) {
		fillColor = color;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillTransparent(int transparent) {
		fillColor = new Color(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue(), transparent);
	}
	
	//影の色
	public void setShadowColor(Color color) {
		shadowColor = color; 
	}
	
	public Color getShadowColor() {
		return shadowColor;
	}
	
	public void setShadowTransparent(int transparent) {
		shadowColor = new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), transparent);
	}
	
	// 線の太さ
	public void setLineWidth(int w) {
		lineWidth = w;
	}

	public int getLineWidth() {
		return lineWidth;
	}

	// 破線
	public void setDashed(boolean b) {
		isDashed = b;
	}

	public boolean getDashed() {
		return isDashed;
	}

	// 影
	public void setShadow(boolean b) {
		isShadow = b;
	}

	public boolean getShadow() {
		return isShadow;
	}

	// 重線
	public void setNLine(int nLine) {
		this.nLine = nLine;
	}

	public int getNLine() {
		return nLine;
	}

	// 選択
	public boolean getSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public void setIsFilter(boolean isFiltered) {
		this.isFiltered = isFiltered;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}
	
	public void setTheta(double theta) {
		this.theta = theta;
	}

	public double getTheta() {
		return theta;
	}
	public MyDrawing clone() {
		try {
			return (MyDrawing) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();

		out.writeInt(lineColor.getRed());
		out.writeInt(lineColor.getBlue());
		out.writeInt(lineColor.getGreen());
		out.writeInt(lineColor.getAlpha());
		out.writeInt(fillColor.getRed());
		out.writeInt(fillColor.getBlue());
		out.writeInt(fillColor.getGreen());
		out.writeInt(fillColor.getAlpha());
		out.writeInt(shadowColor.getRed());
		out.writeInt(shadowColor.getBlue());
		out.writeInt(shadowColor.getGreen());
		out.writeInt(shadowColor.getAlpha());
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();

		int l_red = in.readInt();
		int l_blue = in.readInt();
		int l_green = in.readInt();
		int l_alpha = in.readInt();
		int f_red = in.readInt();
		int f_blue = in.readInt();
		int f_green = in.readInt();
		int f_alpha = in.readInt();
		int s_red = in.readInt();
		int s_blue = in.readInt();
		int s_green = in.readInt();
		int s_alpha = in.readInt();

		lineColor = new Color(l_red, l_green, l_blue, l_alpha);
		fillColor = new Color(f_red, f_green, f_blue, f_alpha);
		shadowColor = new Color(s_red, s_green, s_blue, s_alpha);
		setAllRegion();
	}
}
