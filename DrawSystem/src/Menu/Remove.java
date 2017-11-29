package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import drawSystem.Mediator;

public class Remove extends JMenuItem{
	Mediator mediator;
	public Remove(Mediator mediator) {
		super("削除");
		this.mediator = mediator;
		addActionListener(new RemoveListener());
	}
	
	class RemoveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mediator.removeSelectedDrawing();
		}
	}
}
