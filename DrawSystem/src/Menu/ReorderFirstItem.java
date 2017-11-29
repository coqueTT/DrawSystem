package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import drawSystem.Mediator;

public class ReorderFirstItem extends JMenuItem{
	Mediator mediator;
	public ReorderFirstItem(Mediator mediator) {
		super("最前面へ");
		this.mediator = mediator;
		addActionListener(new ReorderFirstListener());
	}
	
	class ReorderFirstListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			mediator.reorder_first();
		}
	}
}