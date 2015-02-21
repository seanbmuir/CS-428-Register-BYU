package database;

import com.mongodb.*;
import models.*;
import packages.Courses;

public interface IStudentDAO {
    
    public void addStudent(Student student);
    
    public void deleteStudent(Student student);
    
    public void addSection(Section section, Student student);
    
    public void removeSection(Section section, Student student);
    
    public Student getStudent(int id);
    
    public void addCourses(Courses course);
    
    public void removeCourses(Courses course);
}