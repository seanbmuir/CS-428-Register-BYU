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
        collection = db.getCollection("semester");
    }
    
    public void createCollection(){
        db.createCollection("semester", new BasicDBObject("capped", false));
    }
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
//        BasicDBObject searchQuery = new BasicDBObject();
//        searchQuery.put("id",sem_id);
//        DBCursor cursor = collection.find(searchQuery);
//        while (cursor.hasNext())
//        {
//            DBObject semesterObject = cursor.next();
//            String name = semesterObject.get("name").toString();
//            int id = Integer.parseInt(semesterObject.get("id").toString());
//            Semester semester = new Semester(name,id);
//            return semester;
//        }
//        return null;
        String query = "{id : "+sem_id+"}"; //TODO use StringBuilder or something else
        Semester semester = semesters.findOne(query).as(Semester.class);
        return semester;
    }

    @Override
    public void addCourses(Courses course)
    {
        //Model Course needs to be revised
        BasicDBObject document = new BasicDBObject();
        //document.put("name",semester.getName());
        //document.put("id", semester.getID());
        collection.insert(document);
    }

    @Override
    public ArrayList<Semester> getSetOfSemester()
    {
        ArrayList<Semester> semesters = new ArrayList<Semester>();
        DBCursor cursor = collection.find(); //could have an error here, what set of semester are we looking for?
        while (cursor.hasNext())
        {
            DBObject semesterObject = cursor.next();
            //Semester semester = new Semester(semesterObject.get("name").toString(),Integer.parseInt(semesterObject.get("id").toString()));
            //semesters.add(semester);
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
            //Semester semester = new Semester(semesterObject.get("name").toString(),semesterObject.get("id"));
            //semesters.add(semester);
        }
        return courses;
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
        //Model Section needs to be revised
        BasicDBObject searchQuery = new BasicDBObject();
        //searchQuery.put("id", semester.getID());
        DBCursor cursor = collection.find(searchQuery);
        while(cursor.hasNext())
        {
            collection.remove(cursor.next());
        }
    }

    public void removeCourse(Course course, int semID)
    {
        WriteResult result = semesters.update("queryhere").multi().with("updatestuffgoeshere");
    }
}
