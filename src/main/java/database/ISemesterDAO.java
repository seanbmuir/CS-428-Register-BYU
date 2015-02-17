package database;

import models.*;
import java.util.*;

public interface ISemesterDAO {
    
    public void addSemester(Semester semester);
    
    public void deleteSemester(Semester semester);
    
    public Semester getSemester(int sem_id);

    public void addCourses(Course course);
    
    public ArrayList<Semester> getSetOfSemester();
    
    public ArrayList<Course> getCoursesByCredit(int credit);
    
//    public ArrayList<Course> getCoursesByProfessor(Professor professor); // We don't have a model for professor
    
    public ArrayList<Course> getCoursesByTimeOfDay(TimePlace timeplace);
}