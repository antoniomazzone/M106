import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class DayPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel dateLabel;
	private JLabel tempLabel;
	private ArrayList<Time> data;

	public DayPanel() {
		setLayout(new BorderLayout());
		setSize(280, 375);
		Border blackline = BorderFactory.createEtchedBorder();
		setBorder(blackline);
		dateLabel = new JLabel();
		dateLabel.setFont(new Font("Courier", Font.ITALIC, 17));
		dateLabel.setBounds(20, 20, 20, 20);
		dateLabel.setHorizontalAlignment(JLabel.CENTER);

		tempLabel = new JLabel();
		tempLabel.setFont(new Font("Arial", Font.PLAIN, 80));
		tempLabel.setBounds(60, 60, 320, 420);
		tempLabel.setHorizontalAlignment(JLabel.CENTER);
		tempLabel.setVerticalAlignment(JLabel.CENTER);

		add(dateLabel, BorderLayout.NORTH);
		add(tempLabel, BorderLayout.CENTER);
	}

	public void setData(ArrayList<Time> data) {
		this.data = data;
		dateLabel.setText(this.data.get(0).getDay() + ", " + this.data.get(0).getDate());

		int highestTemp = 0;

		for (Time t : this.data) {
			if(t.getTemp() > highestTemp) {
				highestTemp = t.getTemp();
			}
		}

		tempLabel.setText(highestTemp + "°C");

	}
	
	public ArrayList<Time> getData() {
		return this.data;
	}
}
