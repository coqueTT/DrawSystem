package ColorItem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import drawSystem.Mediator;

public class ChangeFillColorButton extends JRadioButton{
	Mediator mediator;
	ColorChangeManager colorManager;
	public ChangeFillColorButton(ColorChangeManager colorManager) {
		super("塗り");
		this.colorManager = colorManager;
		mediator = colorManager.getMediator();
		addActionListener(new ChangeFillListener());
	}
	
	class ChangeFillListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			//if(isSelected())
				colorManager.setState(new ChangeFillColor(mediator));
		}
	}
	
	public class ChangeFillColor implements ColorState{
		Mediator mediator;
		public ChangeFillColor(Mediator mediator){
			this.mediator = mediator;
		}
		
		public void changeColor(Color color) {
			mediator.setFillColor(color);
		}

		@Override
		public void changeTransparent(int transparent) {
			// TODO 自動生成されたメソッド・スタブ
			mediator.setFillTransparent(transparent);
		}
	}
}
