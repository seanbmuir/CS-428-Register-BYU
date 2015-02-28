package database;

import com.mongodb.*;
import models.Course;
import models.Section;
import models.Student;
import org.jongo.Jongo;
import packages.Courses;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sean on 2/17/15.
 */
public class StudentDAO implements IStudentDAO
{



    private MongoClient client;
    private DB db;
    private Jongo jongo = new Jongo();
    public StudentDAO()
    {
        try
        {
            client = new MongoClient("localhost",27017);
            db = client.getDB("test");
        } catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Add one student to the database
     * @param student
     */
    @Override
    public void addStudent(Student student)
    {
        DBCollection collection = db.getCollection("students");
        BasicDBObject object = new BasicDBObject(student.getStudentId(),"MongoDB");
        //Create courses array
        Course[] courses = new Course[student.getPlannedCourses().size()];
        student.getPlannedCourses().toArray(courses);

        BasicDBList dbl = new BasicDBList();
        for(Course course: student.getPlannedCourses())
        {
            BasicDBObject mongoCourse = new BasicDBObject("courseId",course.getCourseID());
            mongoCourse.append("name",course.getCourseName());
            mongoCourse.append("courseNumber",course.getCourseNumber());
            mongoCourse.append("newTitleCode",course.getNewTitleCode());
            mongoCourse.append("department",course.getDepartment());
            mongoCourse.append("departmentCode",course.getDepartmentCode());
            mongoCourse.append("registrationType",course.getRegistrationType());
            dbl.add(mongoCourse);
            //TODO: I don't think we need to worry about adding outcomes or sections for a course here \
        }
        //Create sections array
        Section[] sections = new Section[student.getSchedules().get(0).getClasses().size()];
        student.getSchedules().get(0).getClasses().toArray(sections);


        for(Section section : student.getSchedules().get(0).getClasses())
        {
            BasicDBObject mongoSection = new BasicDBObject("sectionId",section.getSectionID());
            mongoSection.append("credits",section.getCredits());
            mongoSection.append("pid",section.getPid());
            mongoSection.append("professor",section.getProfessor());
            mongoSection.append("rateMyProfId",section.getRateMyProfId());
            mongoSection.append("seatsAvailable",section.getSeatsAvailable());
            mongoSection.append("sectionType",section.getSectionType());
            mongoSection.append("totalSeats",section.getTotalSeats());
            mongoSection.append("courseId", section.getCourseID());
            dbl.add(mongoSection);
        }
//        object.append("netId",student.getStudentId())
//                .append("courses",courses).append("sections",);
        //object.append(new Object())
    }


    private BasicDBList getDBList(String listName,Object[] objects)
    {
        BasicDBList list = new BasicDBList();
        for(int i=0;i<objects.length;i++)
        {
            list.add(i,objects[i]);
        }
        return list;
    }


    /**
     * Delete one student from the database
     * @param student
     */
    @Override
    public void deleteStudent(Student student)
    {
        DBCollection collection = db.getCollection("students");
        collection.remove(new BasicDBObject("netId",student.getStudentId()));
    }

    /**
     * Add a section to the database
     * @param section
     */
    @Override
    public void addSection(Section section, Student student)
    {

    }

    /**
     * Remove a section that
     * @param section
     */
    @Override
    public void removeSection(Section section,Student student)
    {
        //We could remove the student and then add the same student without the section
        deleteStudent(student);
        student.getSchedules().get(0).getClasses().remove(section);
        addStudent(student);
        //collection.remove(new BasicDBObject("netId",student.))
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Student getStudent(int id)
    {
        DBCollection collection = db.getCollection("students");

        List<Student> students = new ArrayList<Student>();

        BasicDBObject query = new BasicDBObject();
        query.put("netId", id);
        DBCursor cursor = collection.find(new BasicDBObject("netId",id));
        while (cursor.hasNext()) {
            DBObject theObj = cursor.next();
            //How to get the DBObject value to ArrayList of Java Object?

            BasicDBList studentsList = (BasicDBList) theObj.get("students");
            for (int i = 0; i < studentsList.size(); i++) {
                BasicDBObject studentObj = (BasicDBObject) studentsList.get(i);
                String firstName = studentObj.getString("firstName");
                String lastName = studentObj.getString("lastName");
                String age = studentObj.getString("age");
                String gender = studentObj.getString("gender");

                Student student = new Student();


                students.add(student);
            }
        }


        return students.get(0);
    }

    /**
     * Add a list of courses to the database
     * @param course
     */
    @Override
    public void addCourses(Courses course, Student student)
    {

    }

    @Override
    public void removeCourses(Courses courses, Student student)
    {
        deleteStudent(student);
        for(Course course : courses.getCourses())
            student.getSchedules().get(0).getClasses().remove(course);
        addStudent(student);
    }

    public MongoClient getClient()
    {
        return client;
    }

    public void setClient(MongoClient client)
    {
        this.client = client;
    }

    public DB getDb()
    {
        return db;
    }

    public void setDb(DB db)
    {
        this.db = db;
    }

}
