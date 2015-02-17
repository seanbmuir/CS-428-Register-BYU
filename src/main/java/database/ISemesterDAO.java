package database;

import com.mongodb.*;
import models.Course;
import models.Semester;
import models.TimePlace;

import java.util.ArrayList;

public interface    ISemesterDAO {
    
    public void addSemester(Semester semester);
    
    public void deleteSemester(Semester semester);
    
    public Semester getSemester(int sem_id);

    public void addCourses(Course course);
    
    public ArrayList<Semester> getSetOfSemester();
    
    public ArrayList<Course> getCoursesByCredit(int credit);
    
    //public ArrayList<Course> getCoursesByProfessor(Professor professor); // We don't have a model for professor
    
    public ArrayList<Course> getCoursesByTimeOfDay(TimePlace timeplace);
}