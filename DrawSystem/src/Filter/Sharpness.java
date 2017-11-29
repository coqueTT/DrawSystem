package Filter;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Sharpness implements Filter {

	@Override
	public BufferedImage filter(BufferedImage img1) {
		System.out.println("先鋭化");
		float[] data = new float[]{
		        -0.1f,-0.1f,-0.1f,-0.1f,-0.1f,
		        -0.1f,-0.1f,-0.1f,-0.1f,-0.1f,
		        -0.1f,-0.1f,3.4f,-0.1f,-0.1f,
		        -0.1f,-0.1f,-0.1f,-0.1f,-0.1f,
		        -0.1f,-0.1f,-0.1f,-0.1f,-0.1f
		};
		
	    BufferedImage img2 = new BufferedImage(img1.getWidth(), img1.getHeight(), img1.getType());
	    Kernel kernel = new Kernel(5, 5, data);
	    ConvolveOp op = new ConvolveOp(kernel);
	    op.filter(img1, img2);
	    
	    return img2;
	}

}
