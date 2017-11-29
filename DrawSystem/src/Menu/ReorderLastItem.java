package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import drawSystem.Mediator;

public class ReorderLastItem extends JMenuItem{
	Mediator mediator;
	public ReorderLastItem(Mediator mediator) {
		super("最背面へ");
		this.mediator = mediator;
		addActionListener(new ReorderLastListener());
	}
	
	class ReorderLastListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			mediator.reorder_last();
		}
	}
}