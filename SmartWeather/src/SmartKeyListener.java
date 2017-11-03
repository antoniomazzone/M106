import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class SmartKeyListener implements KeyListener {
	private MainFrame main;

	public SmartKeyListener(MainFrame root) {
		this.main = root;
	}

	@Override
	public void keyPressed(KeyEvent e) {

		JTextField field = main.getMainScreen().getSearchField();

		if (field.getText().equals("Type in your city (Zuerich,CH)")) {
			field.setText("");
		}

		else {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (!field.getText().equals("")) {
					main.add(new WeatherScreen(field.getText()));
					main.getMainScreen().setVisible(false);
				}
			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
