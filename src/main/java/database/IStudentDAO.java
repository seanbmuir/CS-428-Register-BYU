package database;

import models.Section;
import models.Student;
import packages.Courses;

public interface IStudentDAO {
    
    public void saveStudent(Student student);
    
    public void deleteStudent(Student student);
    
    public void addSection(Section section, Student student);
    
    public void removeSection(Section section, Student student);
    
    public Student getStudent(String id);
    
    public void addCourses(Courses course);
    
    public void removeCourses(Courses course);
}