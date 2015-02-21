package database;

import models.Course;
import models.Section;
import models.Student;
import packages.Courses;

/**
 * Created by sean on 2/17/15.
 */
public class StudentDAO implements IStudentDAO
{
    /**
     * Add one student to the database
     * @param student
     */
    @Override
    public void addStudent(Student student)
    {

    }

    /**
     * Delete one student from the database
     * @param student
     */
    @Override
    public void deleteStudent(Student student)
    {

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

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Student getStudent(int id)
    {
        return null;
    }

    /**
     * Add a list of courses to the database
     * @param course
     */
    @Override
    public void addCourses(Courses course)
    {

    }

    @Override
    public void removeCourses(Courses course)
    {

    }

}
