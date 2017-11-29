package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import drawSystem.Mediator;

public class Paste extends JMenuItem{
	Mediator mediator;
	public Paste(Mediator mediator) {
		super("ペースト");
		this.mediator = mediator;
		addActionListener(new PasteListener());
	}

	class PasteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mediator.paste();
			mediator.repaint();
		}
	}
}