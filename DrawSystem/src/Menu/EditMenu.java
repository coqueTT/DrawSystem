package Menu;

import javax.swing.JMenu;

import drawSystem.MyCanvas;

public class EditMenu extends JMenu{
	public EditMenu(MyCanvas canvas) {
		super("編集");
		
		//メニュー
		ColorMenu colorMenu = new ColorMenu(canvas);
		LineMenu lineMenu = new LineMenu(canvas);
		ShadowMenu shadowMenu = new ShadowMenu(canvas);
		
		//アイテム
		Remove removeItem = new Remove(canvas.getMediator());
		Cut cutItem = new Cut(canvas.getMediator());
		Copy copyItem = new Copy(canvas.getMediator());
		Paste pasteItem = new Paste(canvas.getMediator());
		
		//add
		add(colorMenu);
		add(lineMenu);
		add(shadowMenu);
		add(removeItem);
		add(cutItem);
		add(copyItem);
		add(pasteItem);
	}
}
