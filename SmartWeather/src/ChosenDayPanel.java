import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ChosenDayPanel extends JPanel {

	private JLabel dayLabel;
	private JPanel insidePanel;
	private Border border;

	public ChosenDayPanel() {
		setLayout(null);
		border = BorderFactory.createTitledBorder("");
		setBorder(border);
		dayLabel = new JLabel();
		dayLabel.setBounds(0, 0, 1560, 40);
		add(dayLabel);
	}

	public void setData(ArrayList<Time> data) {

		if (insidePanel != null) {
			remove(insidePanel);
		}
		insidePanel = new JPanel();
		insidePanel.setBounds(5, 50, 1540, 80);

		int x = 20;
		add(insidePanel);
		setBorder(BorderFactory
				.createTitledBorder("Weather of " + data.get(0).getDay() + ", " + data.get(0).getDate()));
		for (int i = 0; i < data.size(); i++) {
			JLabel timeLabel = new JLabel(data.get(i).getTime(), (int) CENTER_ALIGNMENT);
			JLabel tempLabel = new JLabel("" + data.get(i).getTemp() + "°C", (int) CENTER_ALIGNMENT);
			JLabel cloudLabel = new JLabel(data.get(i).getCloudState(), (int) CENTER_ALIGNMENT);

			timeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
			tempLabel.setFont(new Font("Arial", Font.PLAIN, 15));
			cloudLabel.setFont(new Font("Arial", Font.PLAIN, 15));
			
			timeLabel.setBounds(x, 10, 120, 20);
			tempLabel.setBounds(x, 30, 120, 20);
			cloudLabel.setBounds(x, 50, 120, 20);

			insidePanel.add(timeLabel);
			insidePanel.add(tempLabel);
			insidePanel.add(cloudLabel);
			x = x + 180;
		}
	}

}
