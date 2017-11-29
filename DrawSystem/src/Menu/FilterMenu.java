package Menu;

import javax.swing.JMenu;

import drawSystem.MyCanvas;

public class FilterMenu extends JMenu{
	public FilterMenu(MyCanvas canvas) {
		super("フィルター");
		
		//アイテム
		MosaicItem mosaicItem = new MosaicItem(canvas);
		SharpnessItem sharpnessItem = new SharpnessItem(canvas);
		MotionBlurItem motionBlurItem = new MotionBlurItem(canvas);
		LaplacianItem laplacianItem = new LaplacianItem(canvas);
		NonFilterItem nonFilterItem = new NonFilterItem(canvas);
		DarkScaleItem darkScaleItem = new DarkScaleItem(canvas);
		GrayScaleItem grayScaleItem = new GrayScaleItem(canvas);
		OppositeColorItem oppositeColorItem = new OppositeColorItem(canvas);
		
		//add
		add(nonFilterItem);
		add(mosaicItem);
		add(sharpnessItem);
		add(motionBlurItem);
		add(laplacianItem);
		add(darkScaleItem);
		add(grayScaleItem);
		add(oppositeColorItem);
	}
}
