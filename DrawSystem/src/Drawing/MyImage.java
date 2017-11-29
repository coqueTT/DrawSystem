package Drawing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MyImage extends MyDrawing{
	
	public MyImage(File file) {
		super();
		try {
			BufferedImage imI= ImageIO.read(file);
	        img = new BufferedImage(imI.getWidth(),imI.getHeight(),
	                BufferedImage.TYPE_INT_ARGB);
	        Graphics g = img.getGraphics();
	        g.drawImage(imI,0,0,null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setSize(img.getWidth(), img.getHeight());
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
	
	public void draw(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.rotate(getTheta(), getX() + getW() / 2, getY() + getH() / 2);
		if(isFiltered) {
			img2 = filter.filter(img);
			g2.drawImage(img2, null, getX(), getY());
			super.draw(g2);
		}
		else {
			g2.drawImage(img, null, getX(), getY());
			super.draw(g2);
		}
		g2.rotate(-getTheta(), getX() + getW() / 2, getY() + getH() / 2);
	}
}
