package database;

import models.Course;
import models.Semester;
import models.TimePlace;
import packages.Courses;

import java.util.ArrayList;

/**
 * Created by sean on 2/17/15.
 */
public class SemesterDAO implements ISemesterDAO
{
    @Override
    public void addSemester(Semester semester)
    {

    }

    @Override
    public void deleteSemester(Semester semester)
    {

    }

    @Override
    public Semester getSemester(int sem_id)
    {
        return null;
    }

    @Override
    public void addCourses(Courses course)
    {

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
}
