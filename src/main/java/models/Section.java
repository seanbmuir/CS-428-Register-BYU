package models;

import java.util.ArrayList;
import java.util.List;
import org.jongo.marshall.jackson.oid.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Keith
 * Date: 3/14/14
 * Time: 3:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Section {
    private String courseID;
    private String semesterID;
    @Id
    private String sectionID;
    private String sectionType;
    private String professor;
    private String pid;
    private String rateMyProfId;
    private String seatsAvailable;
    private String totalSeats;
    private String waitList;
    private String[] startTimes;
    private String[] endTimes;
    private String[] locations;
    private String[] daysTaught;

    public String getSemesterID()
    {
        return semesterID;
    }

    public void setSemesterID(String semesterID)
    {
        this.semesterID = semesterID;
    }

    public String[] getStartTimes() {
		return startTimes;
	}

	public void setStartTimes(String[] startTimes) {
		this.startTimes = startTimes;
	}

	public String[] getEndTimes() {
		return endTimes;
	}

	public void setEndTimes(String[] endTimes) {
		this.endTimes = endTimes;
	}

	public String[] getLocations() {
		return locations;
	}

	public void setLocations(String[] locations) {
		this.locations = locations;
	}

	public String[] getDaysTaught() {
		return daysTaught;
	}

	public void setDaysTaught(String[] daysTaught) {
		this.daysTaught = daysTaught;
	}





    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    private String credits;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }


    public Section(){

    }

    public String toString(){
        String output = "Section: " + sectionID + "\t" + sectionType + "\t" + professor + "\t";
        for(TimePlace t : getTimePlaces()){
            output += "\n\t\t\t" + t.toString();
        }

        return output;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public List<TimePlace> getTimePlaces() {
    	
    	List<TimePlace> timePlaces = new ArrayList<TimePlace>();
    	for (int i = 0; i < startTimes.length; i++)
    	{
    		String location;
    		if(i >= locations.length)
    			location = locations[0];
    		else
    			location = locations[i];
    			
    		timePlaces.add(new TimePlace(daysTaught[i], startTimes[i], endTimes[i], location));
    	}
    		
    	
    	
    	
    return timePlaces;
    	
    	

    }
    
    public void setTimePlaces(List<TimePlace> timePlaces)
    {
    	int numTimePlaces = timePlaces.size();
    	startTimes = new String[numTimePlaces];
    	endTimes = new String[numTimePlaces];
    	locations = new String[numTimePlaces];
    	daysTaught = new String[numTimePlaces];
    	for(int i = 0; i < numTimePlaces; i++)
    	{
    		startTimes[i] = timePlaces.get(i).getStartTime();
    		endTimes[i] = timePlaces.get(i).getEndTime();
    		locations[i] = timePlaces.get(i).getLocation();
    		daysTaught[i] = timePlaces.get(i).getDay();
    	}
    }


    public String getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(String seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public String getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(String totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getWaitList() {
        return waitList;
    }

    public void setWaitList(String waitList) {
        this.waitList = waitList;
    }

    public String getRateMyProfId() {
        return rateMyProfId;
    }

    public void setRateMyProfId(String rateMyProfId) {
        this.rateMyProfId = rateMyProfId;
    }
}
