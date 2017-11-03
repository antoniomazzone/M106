import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainScreen mainScreen;

	public MainFrame() {
		mainScreen = new MainScreen(this);
		setSize(1640,920);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); 
		add(mainScreen);
		setVisible(true);
		setTitle("SmartWeather");
	}
	
	public MainScreen getMainScreen() {
		return this.mainScreen;
	}

}
 