package ColorItem;

import java.awt.Color;
import drawSystem.Mediator;

public class ColorChangeManager {
	Mediator mediator;
	ColorState state;
	public ColorChangeManager(Mediator mediator) {
		this.mediator = mediator;
	}
	
	public void setState(ColorState state) {
		this.state = state;
	}
	
	public void colorChange(Color color) {
		state.changeColor(color);
	}
	
	public void transparentChange(int transparent) {
		state.changeTransparent(transparent);
	}
	
	public Mediator getMediator() {
		return mediator;
	}
}
