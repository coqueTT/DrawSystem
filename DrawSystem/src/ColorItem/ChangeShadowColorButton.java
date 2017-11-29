package ColorItem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import drawSystem.Mediator;

public class ChangeShadowColorButton extends JRadioButton{
	Mediator mediator;
	ColorChangeManager colorManager;
	public ChangeShadowColorButton(ColorChangeManager colorManager) {
		super("影");
		this.colorManager = colorManager;
		mediator = colorManager.getMediator();
		addActionListener(new ChangeColorListener());
	}
	
	class ChangeColorListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			colorManager.setState(new ChangeShadowColor(mediator));
		}
	}
	
	public class ChangeShadowColor implements ColorState{
		Mediator mediator;
		public ChangeShadowColor(Mediator mediator){
			this.mediator = mediator;
		}
		
		public void changeColor(Color color) {
			mediator.setShadowColor(color);
		}

		@Override
		public void changeTransparent(int transparent) {
			// TODO 自動生成されたメソッド・スタブ
			mediator.setLineTransparent(transparent);
		}
	}
}