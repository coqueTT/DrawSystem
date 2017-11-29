package Filter;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ByteLookupTable;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.awt.image.RescaleOp;

public class OppositeColorFilter implements Filter {

	@Override
	public BufferedImage filter(BufferedImage img1) {
		System.out.println("反対色");
	    byte[] table = new byte[256];
	    for (int i = 0; i < table.length; i++) {
	        table[i] = (byte)(256 - i);
	    }
	    
	    BufferedImage img2 = new BufferedImage(img1.getWidth(), img1.getHeight(), img1.getType());
	    
	    ByteLookupTable blt = new ByteLookupTable(0,table);
	    LookupOp op = new LookupOp(blt,null);
	    op.filter(img1, img2);
	    
	    return img2;
	}

}
