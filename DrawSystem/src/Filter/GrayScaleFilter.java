package Filter;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ByteLookupTable;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.awt.image.RescaleOp;

public class GrayScaleFilter implements Filter {

	@Override
	public BufferedImage filter(BufferedImage img1) {
		System.out.println("グレースケール");
		ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
	    BufferedImage img2 = new BufferedImage(img1.getWidth(), img1.getHeight(), img1.getType());
	    op.filter(img1, img2);
	    
	    return img2;
	}

}
