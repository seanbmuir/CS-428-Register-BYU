package catalogData;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class httpCourseDownloader {
	//private static String[] DEPTS = {"A+HTG", "ACC", "AEROS", "AFRIK", "AM+ST", "ANES", "ANTHR", "ARAB", "ARTHC", "ASIAN", "ASL", "BIO", "BULGN", "BUS+M", "C+S", "CANT", "CE+EN", "CH+EN", "CHEM", "CHIN", "CL+CV", "CLSCS", "CM", "CMLIT", "CMPST", "COMD", "COMMS", "CPSE", "CSANM", "CZECH", "DANCE", "DIGHT", "DUTCH", "EC+EN", "ECE", "ECON", "EDLF", "EIME", "EL+ED", "ELANG", "EMBA", "ENG+T", "ENGL", "ESL", "EUROP", "EXSC", "FIN", "FINN", "FLANG", "FNART", "FPM", "FREN", "GEOG", "GEOL", "GERM", "GREEK", "HCOLL", "HEB", "HIST", "HLTH", "HONRS", "IAS", "ICLND", "IHUM", "INDES", "IP&T", "IR", "IS", "IT", "ITAL", "JAPAN", "KOREA", "LATIN", "LAW", "LFSCI", "LING", "LINGC", "LT+AM", "M+B+A", "M+COM", "MATH", "ME+EN", "MESA", "MFG", "MFHD", "MFT", "MIL+S", "MMBIO", "MTHED", "MUSIC", "NDFS", "NE+LG", "NES", "NEURO", "NORWE", "NURS", "ORG+B", "P+MGT", "P+POL", "PDBIO", "PETE", "PHIL", "PHSCS", "PHY+S", "PL+SC", "POLSH", "PORT", "PSYCH", "PWS", "RECM", "REL+A", "REL+C", "REL+E", "ROM", "RUSS", "SC+ED", "SCAND", "SFL", "SLAT", "SOC", "SOC+W", "SPAN", "STAC", "STAT", "STDEV", "SWED", "T+ED", "TECH", "TEE", "TELL", "TMA", "UNIV", "VA", "VAANM", "VADES", "VAEDU", "VAGD", "VAILL", "VAPHO", "VASTU", "WELSH", "WRTG", "WS"};

    private static List<String> DEPTS = DepartmentDownloader.getHTMLdeptCodes();

    private static String executePost(String targetURL, String urlParameters)
	  {
	    URL url;
	    HttpURLConnection connection = null;  
	    try {
	      //Create connection
	      url = new URL(targetURL);
	      connection = (HttpURLConnection)url.openConnection();
	      connection.setRequestMethod("POST");
	      connection.setRequestProperty("Content-Type", 
	           "application/x-www-form-urlencoded");
				
	      connection.setRequestProperty("Content-Length", "" + 
	               Integer.toString(urlParameters.getBytes().length));
	      connection.setRequestProperty("Content-Language", "en-US");  
				
	      connection.setUseCaches (false);
	      connection.setDoInput(true);
	      connection.setDoOutput(true);

	      //Send request
	      DataOutputStream wr = new DataOutputStream (
	                  connection.getOutputStream ());
	      wr.writeBytes (urlParameters);
	      wr.flush ();
	      wr.close ();

	      //Get Response	
	      InputStream is = connection.getInputStream();
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	      String line;
	      StringBuffer response = new StringBuffer(); 
	      
	      boolean firstLine = true;
	      while((line = rd.readLine()) != null) {
	       
	    	if (firstLine)
	    		firstLine = false;
	    	else
	    		response.append('\r');
	    	response.append(line);
	      }
	      
	      rd.close();
	      return response.toString();

	    } catch (Exception e) {

	      e.printStackTrace();
          System.out.println("Continuing :)");
	      return "";

	    } finally {

	      if(connection != null) {
	        connection.disconnect(); 
	      }
	    }
	  }
	 
    public static String downloadCourses(String semesterCode) throws FileNotFoundException, UnsupportedEncodingException {
        
        StringBuilder writer = new StringBuilder();
        String creditType = "A"; //Figure out what this means, also "S"
        
        for(String dept : DEPTS){
           System.out.println(dept);
           
           String targetURL = "http://saasta.byu.edu/noauth/classSchedule/ajax/searchXML.php";
           String urlParams = "SEMESTER=" + semesterCode + "&CREDIT_TYPE=" + creditType + "&DEPT="+ dept +"&INST=&DESCRIPTION=&DAYFILTER=&BEGINTIME=&ENDTIME=&SECTION_TYPE=&CREDITS=&CREDITCOMP=&CATFILTER=&BLDG=";
           String out = executePost(targetURL, urlParams);


           writer.append(out);
        }
        return removeHtmlFromFile(writer.toString());
       
    }


    private static String removeHtmlFromFile(String rawInput) throws FileNotFoundException {
        String htmlString = rawInput;
        htmlString=htmlString.replaceAll("<br>","\n");
        htmlString=htmlString.replaceAll("</li>","\n");
        String noHTMLString = htmlString.replaceAll("\\<.*?\\>", "");
        return noHTMLString;
    }


    public static List<String> getDepartments() {
    	
    	return DEPTS;
    }
}
