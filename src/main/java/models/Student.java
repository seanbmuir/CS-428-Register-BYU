package models;

import packages.Courses;
import packages.Schedules;
//import java.util.Map;

/**
 * @autor: Nick Humrich
 * @date: 1/17/14
 */
public class Student
{

	private String firstName;
	private String lastName;
	private String studentId;
	private Schedules schedules;
	//private Map<Course, Grade> history;
	private Courses plannedCourses;

	public Student()
	{
	}

	public Student(String studentId)
	{
		this.studentId = studentId;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getStudentId()
	{
		return studentId;
	}

	public void setStudentId(String studentId)
	{
		this.studentId = studentId;
	}

	public Schedules getSchedules()
	{
		return schedules;
	}

	public void setSchedules(Schedules schedules)
	{
		this.schedules = schedules;
	}

//  public Map<Course, Grade> getHistory() {
//    return history;
//  }
//
//  public void setHistory(Map<Course, Grade> history) {
//    this.history = history;
//  }

	public Courses getPlannedCourses()
	{
		return plannedCourses;
	}

	/**
	 * Sets the plannedCourses to this courses object
	 * @param plannedCourses
	 */
	public void setPlannedCourses(Courses plannedCourses)
	{
		this.plannedCourses = plannedCourses;
	}

	/**
	 * Adds more courses to the planned courses
	 * @param courses
	 */
	public void addPlannedCourses(Courses courses)
	{
		if (this.plannedCourses == null)
		{
			this.plannedCourses = courses;
		}
		else
		{
			this.plannedCourses.addCourses(courses);
		}
	}

	public void removePlannedCourses(Courses courses)
	{
		this.plannedCourses.removeCourses(courses);
	}

	public void removePlannedCourse(Course course)
	{
		this.plannedCourses.removeCourse(course);
	}

	public void addPlannedCourse(Course course)
	{
		this.plannedCourses.addCourse(course);
	}

}
