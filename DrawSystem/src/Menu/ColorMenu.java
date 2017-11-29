package Menu;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;

import ColorItem.BlueItem;
import ColorItem.ChangeFillColorButton;
import ColorItem.ChangeLineColorButton;
import ColorItem.ChangeShadowColorButton;
import ColorItem.ColorChangeManager;
import ColorItem.GreenItem;
import ColorItem.OtherColors;
import ColorItem.RedItem;
import drawSystem.MyCanvas;

public class ColorMenu extends JMenu{
	public ColorMenu(MyCanvas canvas) {
		super("カラー");
		
		//色を塗る対称を決めるボタン
		//色をつくるインスタンスを生成
		ColorChangeManager colorManager= new ColorChangeManager(canvas.getMediator());
		ChangeFillColorButton fillRadio = new ChangeFillColorButton(colorManager);
		ChangeLineColorButton lineRadio = new ChangeLineColorButton(colorManager);
		ChangeShadowColorButton shadowRadio = new ChangeShadowColorButton(colorManager);
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(lineRadio);
		radioGroup.add(fillRadio);
		radioGroup.add(shadowRadio);
		RedItem redItem = new RedItem(colorManager);
		GreenItem greenItem = new GreenItem(colorManager);
		BlueItem blueItem = new BlueItem(colorManager);
		OtherColors otherColors = new OtherColors(colorManager);
		
		JMenu transparentMenu = new JMenu("透明度");
		TransparentSlider transparent = new TransparentSlider(colorManager);
		transparentMenu.add(transparent);
		//add
		add(fillRadio);
		add(lineRadio);
		add(shadowRadio);
		add(redItem);
		add(greenItem);
		add(blueItem);
		add(otherColors);
		add(transparentMenu);
	}
}
