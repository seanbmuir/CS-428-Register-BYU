package database;

import com.mongodb.*;

public interface IStudentDAO {
    
    public void addStudent(Student student);
    
    public void deleteStudent(Student student);
    
    public void addSection(Section section);
    
    public void removeSection(Section section);
    
    public Student getStudent(int id);
    
    public void addCourses(Course course);
    
    public void removeCourses(Course course);
}