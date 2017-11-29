package ColorItem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import drawSystem.Mediator;

public class ChangeLineColorButton extends JRadioButton{
	Mediator mediator;
	ColorChangeManager colorManager;
	public ChangeLineColorButton(ColorChangeManager colorManager) {
		super("線");
		this.colorManager = colorManager;
		mediator = colorManager.getMediator();
		addActionListener(new ChangeColorListener());
	}
	
	class ChangeColorListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			colorManager.setState(new ChangeLineColor(mediator));
		}
	}
	
	public class ChangeLineColor implements ColorState{
		Mediator mediator;
		public ChangeLineColor(Mediator mediator){
			this.mediator = mediator;
		}
		
		public void changeColor(Color color) {
			mediator.setLineColor(color);
		}

		@Override
		public void changeTransparent(int transparent) {
			// TODO 自動生成されたメソッド・スタブ
			mediator.setLineTransparent(transparent);
		}
	}
}