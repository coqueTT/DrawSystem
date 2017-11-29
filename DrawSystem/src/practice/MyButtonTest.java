package practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MyButtonTest extends JFrame 
{
	public MyButtonTest() {
		super("MyButtonTest");
		
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		getContentPane().add(jp);
		
		Button rectButton = new Button("Rectangle");
		rectButton.addActionListener(new RectButtonListener());
		jp.add(rectButton);
		
		Button circleButton = new Button("Circle");
		circleButton.addActionListener(new CircleButtonListener());
		jp.add(circleButton);
		
		Button triangleButton = new Button("Triangle");
		triangleButton.addActionListener(new TriangleButtonListener());
		jp.add(triangleButton);
		
		setSize(300, 250);
	}
	
	public static void main(String[] args) {
		MyButtonTest myapp = new MyButtonTest();
		myapp.setVisible(true);
	}
}

//Rectボタンのイベントリスナ
class RectButtonListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Rect is pressed");
	}
}

//Circleボタンのイベントリスナ
class CircleButtonListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Circle is pressed");
	}
}

//Triangleボタンのイベントリスナ
class TriangleButtonListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Triangle is pressed");
	}
}

