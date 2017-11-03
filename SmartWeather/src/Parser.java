import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Parser {
	public Parser() {

	}

	public ArrayList<Time> loadForecastData(String searchString) {
		ArrayList<Time> list = createForecastData("http://api.openweathermap.org/data/2.5/forecast?q=" + searchString
				+ "&mode=xml&appid=590a1a24c268a57e52baeeb88c5d91f0");
		ArrayList<Time> returnList = new ArrayList<Time>();

		String day = "";
		int dayID = 0;

		for (int i = list.size(); i != 0; --i) {
			Time t = list.get(i - 1);
			String tDate = t.getDate();

			if (!day.equals("")) {
				if (tDate.equals(day)) {
					t.setDayID(dayID);
				}

				else {
					day = tDate;
					dayID++;
					t.setDayID(dayID);
				}
			}

			else {
				day = tDate;
				dayID++;
				t.setDayID(dayID);
			}

			returnList.add(t);

		}

		return returnList;
	}

	public ArrayList<String> loadCurrentData(String searchString) {
		return createCurrentData("http://api.openweathermap.org/data/2.5/weather?q=" + searchString
				+ "&mode=xml&appid=590a1a24c268a57e52baeeb88c5d91f0");
	}

	private String getXMLString(String URL) {
		URL url;
		String result = "";
		try {
			url = new URL(URL);

			HttpURLConnection request1 = (HttpURLConnection) url.openConnection();
			request1.setRequestMethod("GET");
			request1.connect();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request1.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null) {

				result = result + line;
			}
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private ArrayList<String> getTimes(String xml) {
		ArrayList<String> timeList = new ArrayList<String>();
		String newString = "";

		while (xml != "") {
			if (xml.equals("")) {
				break;
			} else {
				newString = xml.substring(xml.lastIndexOf("<time "), xml.lastIndexOf("</time") + 7);
				timeList.add(newString);
				xml = xml.substring(xml.indexOf("<time "), xml.lastIndexOf("<time "));
			}
		}

		return timeList;
	}

	private ArrayList<String> createCurrentData(String URL) {
		ArrayList<String> returnList = new ArrayList<String>();

		String xml = getXMLString(URL);
		int currTemp = (int) Double
				.parseDouble(xml.substring(xml.lastIndexOf("<temperature value=") + 20, xml.lastIndexOf("min=") - 2));
		String currClouds = xml.substring(xml.lastIndexOf("<clouds") + 24, xml.lastIndexOf("<visibility") - 11);
		String updateTime = xml.substring(xml.indexOf("<lastupdate") + 30, xml.lastIndexOf("</current>") - 18);

		currTemp = (int) Math.round(currTemp - 273.15);

		returnList.add("" + currTemp);
		returnList.add(currClouds);
		returnList.add(updateTime);

		return returnList;
	}

	private ArrayList<Time> createForecastData(String URL) {
		ArrayList<Time> timeList = new ArrayList<Time>();
		ArrayList<String> strings = getTimes(getXMLString(URL));

		for (String s : strings) {
			Time newTime = new Time();

			String timeDateFrom = s.substring(s.lastIndexOf("from=") + 6, s.lastIndexOf("to=") - 5);
			newTime.setTime(timeDateFrom.substring(timeDateFrom.lastIndexOf("T") + 1));
			newTime.setCloudState(s.substring(s.lastIndexOf("value=") + 7, s.lastIndexOf("all=") - 2));
			newTime.setTemp((int) Math.round(
					Double.parseDouble(s.substring(s.indexOf("temperature") + 33, s.indexOf("min=") - 2)) - 273.15));

			String date = timeDateFrom.split("T")[0];

			try {
				Date date1 = new SimpleDateFormat("yyyy-mm-dd").parse(date);
				String formattedDate = new SimpleDateFormat("dd.mm.yyyy").format(date1);
				newTime.setDay(date);
				newTime.setDate(formattedDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			timeList.add(newTime);

		}

		return timeList;
	}
}
