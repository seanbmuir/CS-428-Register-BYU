package catalogData;

import java.util.ArrayList;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SemesterDownloader{
	
	public static final String HTML_INDEX_PAGE_URL = "http://saasta.byu.edu/noauth/classSchedule/";

	public static void main(String[] args){
		System.out.println("Downloading HTML for Semester Codes...");
		List<String> result = parseSemesterCodes(downloadHTMLIndex());		
		System.out.println(result);
	}

	public static List<String> getSemesterCodes(){
		return parseSemesterCodes(downloadHTMLIndex());
	}

	public static String downloadHTMLIndex() {
		/*
			The Semester codes are hardcoded into the Registrar's Class Schedule website
			with a JavaScript line like:
			var xmlFiles = ["20135","20141","20143","20144","20145","20151","20153","20154"];
		*/

		StringBuffer response = new StringBuffer();
		try{
			URL url = new URL(HTML_INDEX_PAGE_URL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			int responseCode = con.getResponseCode();

			if(responseCode != 200){
				System.out.println("Couldn't query the Registrar's website for semester codes; Response code was: " + responseCode);
				System.exit(0);
			}
			 
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

		}
		catch (Exception e){
			e.printStackTrace();
		}

		return response.toString();
	}

	public static List<String> parseSemesterCodes(String html){
		List <String> semesterCodes = new ArrayList <String>();

		String unparsedCodes = html.split("var xmlFiles = \\[|\\]")[1]; // returns string like: "20135","20141","20143"
		String[] quoted_codes = unparsedCodes.split(",");	// ["20135","20141","20143"]

		for (String s : quoted_codes){
			semesterCodes.add(s.replace("\"", ""));	// [20135, 20141, 20143]
		}

		return semesterCodes;
	}
}














