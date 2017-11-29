package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import Menu.ReorderSubItem.ReorderSubListener;
import drawSystem.Mediator;

public class ReorderPreItem extends JMenuItem{
	Mediator mediator;
	public ReorderPreItem(Mediator mediator) {
		super("1つ前面へ");
		this.mediator = mediator;
		addActionListener(new ReorderPreListener());
	}
	
	class ReorderPreListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			mediator.reorder_pre();
		}
	}
}