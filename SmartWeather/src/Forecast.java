import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Forecast {
	private String cityName;
	private String countryCode;

	private String APIString;
	private String testAPISTRING;

	public Forecast(String cityName, String countryCode) {
		this.cityName = cityName;
		this.countryCode = countryCode;
		this.APIString = "http://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "," + countryCode
				+ "&mode=xml&appid=590a1a24c268a57e52baeeb88c5d91f0";
		
		this.testAPISTRING = "http://api.openweathermap.org/data/2.5/forecast?q=Schlieren,CH&mode=xml&appid=590a1a24c268a57e52baeeb88c5d91f0";
	}
	
	private String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        System.out.println(buffer.toString());
	        return buffer.toString();
	        
	    } finally {
	        if (reader != null)
	            reader.close();
	        
	    }
	}
	
	

}
