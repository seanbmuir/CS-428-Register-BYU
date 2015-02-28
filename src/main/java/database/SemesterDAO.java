package database;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.WriteResult;
import exceptions.DatabaseException;
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
    private final String semesterIDQuery = "{id : #}";
    private MongoCollection semesters;

    public SemesterDAO(DB db){
        this.db = db;
        Jongo jongo = new Jongo(this.db);
        semesters = jongo.getCollection("semester");
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
        WriteResult result = null;
        //WriteResult result = semesters.remove(semester);
        DBValidator.validate(result);
    }

    @Override
    public Semester getSemester(int sem_id)
    {
<<<<<<< Updated upstream
        Semester semester = semesters.findOne(semesterIDQuery).as(Semester.class);
        if(semester == null)
        {
            throw new DatabaseException("Semester not found");
        }
=======
        String query = "{id : "+sem_id+"}";
        Semester semester = semesters.findOne(query).as(Semester.class);
>>>>>>> Stashed changes
        return semester;
    }

    @Override
    public void addCourses(Courses course, int semID)
    {
        String query = "{id : "+semID+"}";
        Semester semester = semesters.findOne(query).as(Semester.class);
        deleteSemester(semester);

        Semester sem2 = new Semester();
        sem2.setCourses(course);
        sem2.setID(semID);
        addSemester(sem2);
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

    public void removeCourse(Course course, int semID)
    {
        String query = "{id : "+semID+"}";
        WriteResult result = semesters.update(query).multi().with("updatestuffgoeshere");
    }
}
