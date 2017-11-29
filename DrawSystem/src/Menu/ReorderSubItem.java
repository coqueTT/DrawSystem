package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import drawSystem.Mediator;

public class ReorderSubItem extends JMenuItem{
	Mediator mediator;
	public ReorderSubItem(Mediator mediator) {
		super("1つ背面へ");
		this.mediator = mediator;
		addActionListener(new ReorderSubListener());
	}
	
	class ReorderSubListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			mediator.reorder_sub();
		}
	}
}
