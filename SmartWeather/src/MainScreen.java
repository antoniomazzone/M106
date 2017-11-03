
import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainScreen extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JTextField cityTextField;
	private MainFrame root;

	public MainScreen(MainFrame mf) {
		this.root = mf;
		setSize(1640,920);
		setLayout(null);
		initializeComponents();
		setVisible(true);
	}

	private void initializeComponents() {
		label = new JLabel("SmartWeather", (int) CENTER_ALIGNMENT);
		cityTextField = new JTextField("Type in your city (Zuerich,CH)");

		label.setFont(new Font("Arial", Font.PLAIN, 45));
		label.setBounds(375, 290, 850, 50);

		cityTextField.setHorizontalAlignment(JTextField.CENTER);
		cityTextField.setFont(new Font("Arial", Font.PLAIN, 30));
		cityTextField.setForeground(Color.LIGHT_GRAY);

		cityTextField.setBounds(400, 365, 800, 70);


		add(label);
		add(cityTextField);
		
		cityTextField.addKeyListener(new SmartKeyListener(this.root));

		JComponent[] compArr = new JComponent[] { cityTextField};
		setMouseListener(compArr);
	}

	private void setMouseListener(JComponent[] arr) {
		for (JComponent c : arr) {
			c.addMouseListener(new SmartMouseListener(null,c));
		}
	}
	
	public JTextField getSearchField() {
		return cityTextField;
	}
}
