import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class WeatherScreen extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private DayPanel day1;
	private DayPanel day2;
	private DayPanel day3;
	private DayPanel day4;
	private DayPanel day5;
	private Parser parser = new Parser();
	private ArrayList<Time> forecastData;
	private int tempNow;
	private String lastUpdate;
	private JLabel tempNowLabel;
	private JLabel updateLabel;

	private ChosenDayPanel chosenDay;

	public WeatherScreen(String searchString) {
		this.forecastData = this.parser.loadForecastData(searchString);
		this.tempNow = Integer.parseInt(this.parser.loadCurrentData(searchString).get(0));
		this.lastUpdate = this.parser.loadCurrentData(searchString).get(2);
		setSize(1640, 920);
		setLayout(null);
		constructComponents(searchString.split(",")[0]);
		setVisible(true);
	}

	private void constructComponents(String city) {
		label = new JLabel("Prognose of " + city, (int) CENTER_ALIGNMENT);
		updateLabel = new JLabel("<html><font color='red'>Last update: " + lastUpdate +"</font></html>");
		tempNowLabel = new JLabel("", (int) CENTER_ALIGNMENT);

		day1 = new DayPanel();
		day2 = new DayPanel();
		day3 = new DayPanel();
		day4 = new DayPanel();
		day5 = new DayPanel();

		label.setFont(new Font("Arial", Font.PLAIN, 35));
		label.setBounds(0, 25, 1640, 50);

		updateLabel.setBounds(1500, 10, 200, 50);
		tempNowLabel.setFont(new Font("Arial", Font.PLAIN, 130));
		tempNowLabel.setBounds(0, 100, 1640, 150);

		day1.setLocation(40, 480);
		day2.setLocation(360, 480);
		day3.setLocation(680, 480);
		day4.setLocation(1000, 480);
		day5.setLocation(1320, 480);

		chosenDay = new ChosenDayPanel();
		chosenDay.setBounds(40, 270, 1560, 150);

		JComponent[] compArr = new JComponent[] { day1, day2, day3, day4, day5, chosenDay };
		setMouseListener(compArr);

		loadDataInComponents();
	}

	private void addAllComponents() {
		add(label);
		add(day1);
		add(day2);
		add(day3);
		add(day4);
		add(day5);
		add(chosenDay);
		add(tempNowLabel);
		add(updateLabel);
	}

	private void loadDataInComponents() {
		ArrayList<Time> listDay1 = new ArrayList<Time>();
		ArrayList<Time> listDay2 = new ArrayList<Time>();
		ArrayList<Time> listDay3 = new ArrayList<Time>();
		ArrayList<Time> listDay4 = new ArrayList<Time>();
		ArrayList<Time> listDay5 = new ArrayList<Time>();

		for (Time t : this.forecastData) {
			switch (t.getDayID()) {
			case 1:
				listDay1.add(t);
				break;

			case 2:
				listDay2.add(t);
				break;

			case 3:
				listDay3.add(t);
				break;

			case 4:
				listDay4.add(t);
				break;

			case 5:
				listDay5.add(t);
				break;

			default:
				break;
			}
		}

		day1.setData(listDay1);
		day2.setData(listDay2);
		day3.setData(listDay3);
		day4.setData(listDay4);
		day5.setData(listDay5);

		tempNowLabel.setText(tempNow + "°C");
		addAllComponents();
	}

	private void setMouseListener(JComponent[] arr) {
		for (JComponent c : arr) {
			c.addMouseListener(new SmartMouseListener(this, c));
		}
	}

	public ChosenDayPanel getChosenDayPanel() {
		return this.chosenDay;
	}

	public void setChosenDayPanel(ChosenDayPanel cdp) {
		this.chosenDay = cdp;
	}

}
