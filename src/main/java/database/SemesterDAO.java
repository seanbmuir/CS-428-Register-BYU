package database;

import models.Course;
import models.Section;
import models.Semester;
import models.TimePlace;
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
    
    public SemesterDAO(DB db){
        this.db = db;
        collection = db.getCollection("semester");
    }
    
    public void createCollection(){
        db.createCollection("semester", new BasicDBObject("capped", false));
    }
    @Override
    public void addSemester(Semester semester)
    {
        BasicDBObject document = new BasicDBObject();
        document.put("name",semester.getName());
        document.put("id", semester.getID());
        collection.insert(document);
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
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("id",sem_id);
        DBCursor cursor = collection.find(searchQuery);
        while (cursor.hasNext())
        {
            DBObject semesterObject = cursor.next();
            //Semester semester = new Semester(semesterObject.get("name").toString(),Integer.parseInt(semesterObject.get("id").toString()));
            //return semester;
        }
        return null;
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
            //Semester semester = new Semester(semesterObject.get("name").toString(),Integer.parseInt(semesterObject.get("id").toString()));
            //semesters.add(semester);
        }
        //return semesters;
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
}
