package database;

import com.mongodb.*;
import models.Course;
import models.Section;
import models.Student;

public interface IStudentDAO {
    
    public void addStudent(Student student);
    
    public void deleteStudent(Student student);
    
    public void addSection(Section section);
    
    public void removeSection(Section section);
    
    public Student getStudent(int id);
    
    public void addCourses(Course course);
    
    public void removeCourses(Course course);
}