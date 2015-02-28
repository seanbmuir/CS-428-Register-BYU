package database;

import com.mongodb.*;
import models.Course;
import models.Section;
import models.Semester;
import models.TimePlace;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import packages.Courses;

import java.util.ArrayList;

/**
 * Created by sean on 2/17/15.
 */
public class SemesterDAO implements ISemesterDAO
{
    private DB db;
    private DBCollection collection;
    private MongoCollection semesters;

    public SemesterDAO(DB db){
        this.db = db;
        Jongo jongo = new Jongo(this.db);
        semesters = jongo.getCollection("semester");
        //collection = db.getCollection("semester");
    }
    
    //public void createCollection(){
    //    db.createCollection("semester", new BasicDBObject("capped", false));
    //}
    @Override
    public void addSemester(Semester semester)
    {
        WriteResult result = semesters.save(semester);
        DBValidator.validate(result);
    }

    @Override
    public void deleteSemester(Semester semester)
    {
    
    }

    @Override
    public Semester getSemester(int sem_id)
    {
        String query = "{id : "+sem_id+"}"; //TODO use StringBuilder or something else
        Semester semester = semesters.findOne(query).as(Semester.class);
        return semester;
    }

    @Override
    public void addCourses(Courses course)
    {

    }

    @Override
    public ArrayList<Semester> getSetOfSemester()
    {
        return null;
    }

    @Override
    public ArrayList<Course> getCoursesByCredit(int credit)
    {
        return null;
    }

    @Override
    public ArrayList<Course> getCoursesByTimeOfDay(TimePlace timeplace)
    {
        return null;
    }

    @Override
    public void addSection(Course course, Section section)
    {

    }

    @Override
    public void removeSection(Section section)
    {

    }

    public void removeCourse(Course course, int semID)
    {
        WriteResult result = semesters.update("queryhere").multi().with("updatestuffgoeshere");
    }
}
