package Filter;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RescaleOp;

public class DarkScaleFilter implements Filter {

	@Override
	public BufferedImage filter(BufferedImage img1) {
		System.out.println("ダークスケール");
		float[] f = {0.5f, 0.5f, 0.5f, 1.0f};
		float[] d = {0, 0, 0, 0};
	    BufferedImage img2 = new BufferedImage(img1.getWidth(), img1.getHeight(), img1.getType());
	    RescaleOp op = new RescaleOp(f,d,null);
	    op.filter(img1, img2);
	    
	    return img2;
	}
}
