package practice;

import javax.swing.*;
import java.awt.event.*;

public class MyMouseEventTest extends JFrame 
{
	MyMouseEventTest() {
		super("Mouse Event Test");
		addMouseListener(new MyMouseListener());
		setSize(100, 100);
	}
	
	public static void main(String[] args) {
		MyMouseEventTest myapp = new MyMouseEventTest();
		myapp.setVisible(true);
	}
}

class MyMouseListener extends MouseAdapter
{
	public void mouseClicked(MouseEvent e) {
		System.out.println("ド変態");
	}
}
