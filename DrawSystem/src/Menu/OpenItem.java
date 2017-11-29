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

public class OpenItem extends JMenuItem{
	Mediator mediator;
	public OpenItem(MyCanvas canvas) {
		super("開く");
		mediator = canvas.getMediator();
		addActionListener(new OpenListener());
	}
	
	class OpenListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// File入力
			try {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);  // ファイルロード用のダイアログを開く

				if (returnVal == JFileChooser.APPROVE_OPTION) {  // OKボタンが押されたとき
					File file = fc.getSelectedFile();

					FileInputStream fin = new FileInputStream(file);
					ObjectInputStream in = new ObjectInputStream(fin);
					@SuppressWarnings("unchecked")
					Vector<MyDrawing> drawings = (Vector<MyDrawing>)in.readObject();
					mediator.setDrawings(drawings);

					//			    MyDrawing d = (MyDrawing)in.readObject();
					//			    System.out.println("read:" + d.getLineWidth());
					//			    mediator.addDrawing(d);
					mediator.repaint();
					fin.close();
				}
			} catch (Exception ex) {
			}
		}
	}
}
