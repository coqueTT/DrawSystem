package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import drawSystem.Mediator;

public class Cut extends JMenuItem{
	Mediator mediator;
	public Cut(Mediator mediator) {
		super("カット");
		this.mediator = mediator;
		addActionListener(new CutListener());
	}
	
	class CutListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mediator.cut();
		}
	}
}