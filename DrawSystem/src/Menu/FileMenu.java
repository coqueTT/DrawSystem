package Menu;

import javax.swing.JMenu;

import drawSystem.MyCanvas;

public class FileMenu extends JMenu{
	public FileMenu(MyCanvas canvas) {
		super("ファイル");
		
		//アイテム
		OpenItem openItem = new OpenItem(canvas);
		ExportItem exportItem = new ExportItem(canvas);
		OpenImageItem openImageItem = new OpenImageItem(canvas);
		
		//add
		add(openItem);
		add(exportItem);
		add(openImageItem);
	}
}
