package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import Drawing.MyDrawing;
import Drawing.MyImage;
import drawSystem.Mediator;
import drawSystem.MyCanvas;

public class OpenImageItem extends JMenuItem{
	Mediator mediator;
	public OpenImageItem(MyCanvas canvas) {
		super("画像");
		mediator = canvas.getMediator();
		addActionListener(new OpenImageListener());
	}
	
	class OpenImageListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// File入力
			try {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);  // ファイルロード用のダイアログを開く

				if (returnVal == JFileChooser.APPROVE_OPTION) {  // OKボタンが押されたとき
					File file = fc.getSelectedFile();
					
					MyImage img = new MyImage(file);
					mediator.addDrawing(img);
					mediator.repaint();
					System.out.println("画像を読み込みました");
				}
			} catch (Exception ex) {
			}
		}
	}
}
