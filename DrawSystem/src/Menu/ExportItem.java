package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import Drawing.MyDrawing;
import drawSystem.Mediator;
import drawSystem.MyCanvas;

public class ExportItem extends JMenuItem{
	Mediator mediator;
	public ExportItem(MyCanvas canvas) {
		super("保存");
		mediator = canvas.getMediator();
		addActionListener(new ExportListener());
	}
	
	class ExportListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// File出力
			try {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showSaveDialog(null);  // ファイルロード用のダイアログを開く

				if (returnVal == JFileChooser.APPROVE_OPTION) {  // OKボタンが押されたとき
					File file = fc.getSelectedFile();

					FileOutputStream fout = new FileOutputStream(file);
					ObjectOutputStream out = new ObjectOutputStream(fout);
					//			    MyDrawing d = mediator.getDrawings().firstElement();
					//			    out.writeObject(d);

					Vector<MyDrawing> drawings = mediator.getDrawings();
					out.writeObject(drawings);
					out.flush();

					fout.close();
				}
			} catch (Exception ex) {
			}
		}
	}
}
