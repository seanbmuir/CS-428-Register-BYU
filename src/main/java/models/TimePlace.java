package models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: Keith
 * Date: 3/14/14
 * Time: 3:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class TimePlace {
    private String day;
    private String startTime;
    private String endTime;
    private String location;

    public TimePlace() {

    }

    public TimePlace(JSONObject jsonBody) throws JSONException{
        this();

        setDay(jsonBody.get("day").toString());
        setStartTime(jsonBody.get("startTime").toString());
        setEndTime(jsonBody.get("endTime").toString());
        setLocation(jsonBody.get("location").toString());
    }

    public TimePlace(String day, String startTime, String endTime, String location) {
        this();
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    public String toString(){
        return "TimePlace " + day + "\t" + startTime + "\t" + endTime + "\t" + location;
    }
    public void setDay(String day) {
        this.day = day;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }
}
