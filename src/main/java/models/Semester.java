package models;

import packages.Courses;

/**
 * @author: Nick Humrich
 * @date: 1/17/14
 */
public class Semester
{

	private String name;
	private int id;
	private Courses courses;


	public Semester(String name, int id)
	{
		this.name = name;
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getID()
	{
		return id;
	}

	public void setID(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Courses getCourses()
	{
		return courses;
	}

	public void setCourses(Courses courses)
	{
		this.courses = courses;
	}
}
