package database;

import models.Course;
import models.Section;
import models.Semester;
import models.TimePlace;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import packages.Courses;

import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * Created by sean on 2/17/15.
 */
public class SemesterDAO implements ISemesterDAO
{
    private DB db;
    private DBCollection collection;
    private final String semesterIDQuery = "{id : #}";
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
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("id", semester.getID());
        DBCursor cursor = collection.find(searchQuery);
        while(cursor.hasNext())
        {
            collection.remove(cursor.next());
        }
    }

    @Override
    public Semester getSemester(int sem_id)
    {
        Semester semester = semesters.findOne(semesterIDQuery).as(Semester.class);
        if(semester == null)
        {
            throw new DatabaseException("Semester not found");
        }
        return semester;
    }

    @Override
    public void addCourses(Courses course)
    {

    }

    @Override
    public ArrayList<Semester> getSetOfSemester()
    {
        ArrayList<Semester> semesters = new ArrayList<Semester>();
        DBCursor cursor = collection.find(); //could have an error here, what set of semester are we looking for?
        while (cursor.hasNext())
        {
            DBObject semesterObject = cursor.next();
            Semester semester = new Semester(semesterObject.get("name").toString(),Integer.parseInt(semesterObject.get("id").toString()));
            semesters.add(semester);
        }
        return semesters;
    }

    @Override
    public ArrayList<Course> getCoursesByCredit(int credit)
    {
        ArrayList<Course> courses = new ArrayList<Course>();
        DBCursor cursor = collection.find(); //could have an error here, what set of semester are we looking for?
        while (cursor.hasNext())
        {
            DBObject semesterObject = cursor.next();
            Semester semester = new Semester(semesterObject.get("name").toString(),Integer.parseInt(semesterObject.get("id").toString()));
            semesters.add(semester);
        }
        return semesters;
    }

    @Override
    public ArrayList<Course> getCoursesByTimeOfDay(TimePlace timeplace)
    {
        return null;
    }

    @Override
    public void addSection(Course course, Section section)
    {
        //Model Section needs to be revised
        BasicDBObject document = new BasicDBObject();
        //document.put("name",semester.getName());
        //document.put("id", semester.getID());
        collection.insert(document);
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
