package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import drawSystem.Mediator;

public class Shadow extends JMenuItem{
	Mediator mediator;
	public Shadow(Mediator mediator) {
		super("ドロップシャドウ");
		this.mediator = mediator;
		addActionListener(new ShadowListener());
	}

	class ShadowListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mediator.setShadow();
		}
	}
}
