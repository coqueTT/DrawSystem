package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import drawSystem.Mediator;

public class Copy extends JMenuItem{
	Mediator mediator;
	public Copy(Mediator mediator) {
		super("コピー");
		this.mediator = mediator;
		addActionListener(new CopyListener());
	}
	
	class CopyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mediator.copy();
			mediator.repaint();
		}
	}
}