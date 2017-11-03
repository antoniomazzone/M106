import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SmartMouseListener implements MouseListener {
	private JComponent comp;
	private String text;
	private WeatherScreen ws;
	

	public SmartMouseListener(WeatherScreen ws, JComponent c) {
		this.comp = c;
		if(ws != null) {
			this.ws = ws;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (comp.getClass().isInstance(new JTextField())) {
			JTextField textfield = (JTextField) this.comp;
			this.text = textfield.getText();
			textfield.setText("");

		}
		if (comp.getClass().isInstance(new JLabel())) {
			System.out.println("Label");
		}
		
		if(comp.getClass().isInstance(new DayPanel())) {
			DayPanel panel = (DayPanel) this.comp;
			this.ws.getChosenDayPanel().setData(panel.getData());	
			ws.getChosenDayPanel().repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (comp.getClass().isInstance(new DayPanel())) {
			comp.setBounds(comp.getX() - 15, comp.getY() - 50, comp.getWidth() + 30, comp.getHeight() + 50);
			comp.setBackground(Color.lightGray);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (comp.getClass().isInstance(new DayPanel())) {
			comp.setBounds(comp.getX() + 15, comp.getY() + 50, comp.getWidth() - 30, comp.getHeight() - 50);
			comp.setBackground(null);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
