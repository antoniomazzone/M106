import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Time {
	private int dayID;
	private String date;
	private String day;
	private String time;
	private String cloudState;
	private int temp;

	public Time() {

	}

	public int getDayID() {
		return dayID;
	}

	public void setDayID(int dayID) {
		this.dayID = dayID;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCloudState() {
		return cloudState;
	}

	public void setCloudState(String cloudState) {
		this.cloudState = cloudState;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {

		this.date = date;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String formattedDate) throws ParseException {
		
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		Date date1 = new SimpleDateFormat("yyyy-mm-dd").parse(formattedDate);
		c.setTime(date1);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

		switch (dayOfWeek) {
		case 1:
			this.day = "Donnerstag";
			break;
		case 2:
			this.day = "Freitag";
			break;
		case 3:
			this.day = "Samstag";
			break;
		case 4:
			this.day = "Sonntag";
			break;
		case 5:
			this.day = "Montag";
			break;
		case 6:
			this.day = "Dienstag";
			break;
		case 7:
			this.day = "Mittwoch";
			break;
		}
	}

}
