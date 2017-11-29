package Filter;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Laplacian implements Filter {

	@Override
	public BufferedImage filter(BufferedImage img1) {
		System.out.println("エッジ抽出");
		float[] data = new float[]{
		        0.0f, -1.0f, 0.0f,
		        -1.0f, 4.0f, -1.0f,
		        0.0f, -1.0f, 0.0f
		};
		
	    BufferedImage img2 = new BufferedImage(img1.getWidth(), img1.getHeight(),BufferedImage.TYPE_INT_RGB);
	    Kernel kernel = new Kernel(3, 3, data);
	    ConvolveOp op = new ConvolveOp(kernel);
	    op.filter(img1, img2);
	    return img2;
	}

}
